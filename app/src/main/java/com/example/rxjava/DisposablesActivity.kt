package com.example.rxjava

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.data.DataSource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class DisposablesActivity : AppCompatActivity() {
    lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        compositeDisposable = CompositeDisposable()


        val observable = Observable.fromIterable(DataSource.createTaskList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())


        observable.subscribe({

        }, {

        }, {

        }, { disposable ->
            compositeDisposable.add(disposable)

        })



        val disposable = observable.subscribe(Consumer {

        })

        compositeDisposable.add(disposable)


        compositeDisposable.add(observable.subscribe(Consumer {

        }))
    }


    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable.clear()


        //Dispose is like hard clear
//        compositeDisposable.dispose()

    }
}