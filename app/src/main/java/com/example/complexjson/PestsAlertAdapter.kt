package com.example.complexjson

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
//import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.myrequests.view.*

class PestsAlertAdapter(val context: Context, val userList: List<PestAlertRequests>):RecyclerView.Adapter<PestsAlertAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var subject: TextView
        var description: TextView
        var image: ImageView
        init {
            subject = itemView.subjectpest
            description = itemView.descriptionrequest
            image = itemView.requestimagepest
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PestsAlertAdapter.ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.myrequests, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.subject.text = userList[position].subject
        holder.description.text = userList[position].description
//        Log.i("STUFF", userList[position].images[position].url)
//        for (i in 0 until position) {
//           Log.d("stuff", userList[i].images[i].url)
////           Log.d("stuff", position.toString())
//        }
//        for (int i = 0; i < position; i++) {
//
//        }
//        holder.image.adjustViewBounds = userList[position].images[position].url
//        val testimage = userList[position].images[position].url
//        val imagepest = holder.image.requestimagepest
//        Picasso.with(holder.image.context).load(userList[position].images[position].url).into(imagepest)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

}