package com.example.rxjava

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.data.DataSource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers

class ObserverObservableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val observable = Observable.fromIterable(DataSource.createTaskList())
                .subscribeOn(Schedulers.io())
                .filter(Predicate {
                    println("Thread: ${Thread.currentThread().name}")

                    Thread.sleep(2000)

                    return@Predicate it.isCompleted
                })
                .observeOn(AndroidSchedulers.mainThread())


//        val observer= object : Observer<Task>{
//            override fun onComplete() {
//            }
//
//            override fun onSubscribe(d: Disposable) {
//            }
//
//            override fun onNext(t: Task) {
//            }
//
//            override fun onError(e: Throwable) {
//            }
//
//
//        }


        observable.subscribe(
                { value ->

                    println("Thread: ${Thread.currentThread().name}")

                    println("Received: ${value.description}")



                }, // onNext
                { error -> println("Error: $error") },    // onError
                { println("Completed!") }
        )
    }
}