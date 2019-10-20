package com.example.rxjava

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.ProgressBar
import android.widget.TextView
import com.example.data.Post


class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {


    private val TAG = "RecyclerAdapter"

    private var posts = ArrayList<Post>()

    override fun getItemCount(): Int {
        Log.d(TAG, posts.size.toString())
        return posts.size
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, pos: Int) {

        bind(posts[pos],viewHolder)

    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, null, false)
        return MyViewHolder(view)
    }

    fun setPosts(posts: List<Post>) {
        this.posts = posts as ArrayList<Post>
        notifyDataSetChanged()
    }
    fun updatePost(post: Post) {
        posts[posts.indexOf(post)] = post
        notifyItemChanged(posts.indexOf(post))
    }


    fun bind(post: Post, viewHolder: MyViewHolder) {
        viewHolder.tvDesc.setText(post.getTitle())

        if (post.getComments() == null) {
            showProgressBar(true,viewHolder)
            viewHolder.tvCount.setText("")
        } else {
            showProgressBar(false,viewHolder)
            viewHolder.tvCount.setText(post.getComments()!!.size.toString())
        }
    }


    private fun showProgressBar(showProgressBar: Boolean, viewHolder: MyViewHolder) {
        if (showProgressBar) {
            viewHolder.pb.setVisibility(View.VISIBLE)
        } else {
            viewHolder.pb.setVisibility(View.GONE)
        }
    }

    class MyViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        var tvCount = item.findViewById<TextView>(R.id.tvCount)
        var tvDesc = item.findViewById<TextView>(R.id.tvDesc)
        var pb = item.findViewById<ProgressBar>(R.id.pb)


    }
}