package com.example.retrofit

import com.example.data.Comment
import retrofit2.http.GET
import com.example.data.Post
import io.reactivex.Observable
import retrofit2.http.Path


interface RequestApi {

    @GET("/posts")
    fun getPosts(): Observable<List<Post>>

    @GET("posts/{id}/comments")
    fun getComments(
            @Path("id") id: Int
    ): Observable<List<Comment>>
}