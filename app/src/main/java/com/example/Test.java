package com.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.data.DataSource;
import com.example.data.Task;
import com.example.rxjava.R;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.functions.Function;

public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Observable<String> observable=Observable.fromIterable(DataSource.Companion.createTaskList())
                .map(new Function<Task, String>() {
                    @Override
                    public String apply(Task task) throws Exception {
                        return null;
                    }
                });

        Observable<String> observable1=Observable.fromIterable(DataSource.Companion.createTaskList())
                .flatMap(new Function<Task, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Task task) throws Exception {
                        return null;
                    }
                });
    }
}
