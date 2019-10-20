package com.example.rxjava

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.data.DataSource
import com.example.data.Task
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CreationalOperatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        onCreateObservable()
        justOperator()
        rangeOperator()
        repeatOperator()


    }

    private fun repeatOperator() {
        val repeatOperator=Observable.range(1,3)
                .repeat(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())


        val subscriber=   repeatOperator.subscribe({
            Log.d("TaskEmitted", "String $it")

        })    }

    private fun rangeOperator() {
        val rangeOperator=Observable.range(1,7)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())


        val subscriber=   rangeOperator.subscribe({
            Log.d("TaskEmitted", "String $it")

        })

    }

    fun justOperator(){

        val observableJust=Observable.just("ashu")

     val subscriber=   observableJust.subscribe({
            Log.d("TaskEmitted", it)

        })
    }

    /**
     * It convert task into Observable
     */
    private fun onCreateObservable() {
//        val task = Task("I am ashu", true, 2)
        val tasks = DataSource.createTaskList()

        val observableCreate = Observable.create<Task> {

            if (!it.isDisposed) {

                for (task in tasks){
                    it.onNext(task)
                }

            }

            if (!it.isDisposed)
                it.onComplete()

        }

        val observer = object : Observer<Task> {
            override fun onComplete() {
                Log.d("TaskEmitted", "OnComplete")

            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: Task) {

                Log.d("TaskEmitted", t.description);
            }

            override fun onError(e: Throwable) {
            }

        }

        observableCreate.subscribe(observer)
    }
}