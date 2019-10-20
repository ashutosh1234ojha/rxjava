package com.example.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Post(userId: Int, id: Int, title: String, body: String, comments: List<Comment>) {


    @SerializedName("userId")
    @Expose
    private var userId: Int = 0

    @SerializedName("id")
    @Expose
    private var id: Int = 0

    @SerializedName("title")
    @Expose
    private var title: String? = null

    @SerializedName("body")
    @Expose
    private var body: String? = null

    private var comments: List<Comment>? = null



    fun getUserId(): Int {
        return userId
    }

    fun setUserId(userId: Int) {
        this.userId = userId
    }

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getBody(): String? {
        return body
    }

    fun setBody(body: String) {
        this.body = body
    }

    fun getComments(): List<Comment>? {
        return comments
    }

    fun setComments(comments: List<Comment>) {
        this.comments = comments
    }

    override fun toString(): String {
        return "Post{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\''.toString() +
                ", body='" + body + '\''.toString() +
                '}'.toString()
    }
}