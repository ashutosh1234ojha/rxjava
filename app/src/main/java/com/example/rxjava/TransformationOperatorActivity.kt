package com.example.rxjava

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Button
import com.example.data.DataSource
import com.example.data.Post
import com.example.retrofit.ServiceGenerator
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class TransformationOperatorActivity : AppCompatActivity() {

    lateinit var rvList: RecyclerView
    lateinit var listAdapter: ListAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        mapOperator()
//        flatMapOperator()
        bufferOperator()


    }

    private fun bufferOperator() {

        Observable.fromIterable(DataSource.createTaskList())
                .buffer(2)
                .subscribe {

                    Log.d("BufferOperator", "------------------")

                    for (task in it) {
                        Log.d("BufferOperator", task.description)
                    }
                }
    }

    fun init() {
        rvList = findViewById(R.id.rvList)
        listAdapter = ListAdapter()
        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = listAdapter
    }

    /**
     * This method tasks a list of object and convert it into string
     */
    fun mapOperator() {

        val stringObservable = Observable
                .fromIterable(DataSource.createTaskList())
                .map {
                    it.description
                }


        val subscribe = stringObservable.subscribe {

            Log.d("EmittedData", it)

        }

    }

    fun flatMapOperator() {


        getPostObservables()
                .flatMap {

                    Log.d("EmittedData", "Flatmap")

                    return@flatMap getCommentObservable(it)

                }
                .subscribe(object : Observer<Post> {
                    override fun onComplete() {


                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: Post) {
                        listAdapter.updatePost(post = t)
                    }

                    override fun onError(e: Throwable) {
                    }


                })


    }

    /**
     * Here we get the list of Comments using api and set that comment to the corresponding post
     */
    fun getCommentObservable(post: Post): Observable<Post>? {

        return ServiceGenerator.getRequestApi().getComments(post.getId())
                .map {
                    post.setComments(it)

                    return@map post
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    }

    /**
     * First we get the list of Post using api
     * Then we apply fromIterable operator to get one-one Post
     * It returns Observable of Post
     */
    fun getPostObservables(): Observable<Post> {

        return ServiceGenerator.getRequestApi().getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap {

                    Log.d("EmittedData", "Flatmap")

                    listAdapter.setPosts(it)


                    return@flatMap Observable.fromIterable(it)

                }
    }
}