package com.example.complexjson

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RvAdapter(ctx: Context, private val dataModelArrayList: ArrayList<DataModel>, private val imageArrayList: ArrayList<DataModel.ImageUrl>) :
    RecyclerView.Adapter<RvAdapter.MyViewHolder>() {

    private val inflater: LayoutInflater

    init {

        inflater = LayoutInflater.from(ctx)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvAdapter.MyViewHolder {

        val view = inflater.inflate(R.layout.rv_one, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RvAdapter.MyViewHolder, position: Int) {
//        for (i in 0..(position)) {
//            Log.i("stuff", dataModelArrayList[i].imagepath!![position].getimageurl())
////            Log.d("stuff", position.toString())
//        }
//        for (i in 0 until position-1) {
//            Log.i("ImgErr", dataModelArrayList[i].imagepath.toString())
//            Log.i("ImgErr", dataModelArrayList[i].description.toString())
//        }
//        Picasso.with(holder.iv.context).load(imageArrayList[position].url).into(holder.iv)
        holder.name.setText(dataModelArrayList[position].subject)
        holder.country.setText(dataModelArrayList[position].description)
        holder.city.setText(dataModelArrayList[position].city)
    }

    override fun getItemCount(): Int {
        return dataModelArrayList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var country: TextView
        var name: TextView
        var city: TextView
        var iv: ImageView

        init {

            country = itemView.findViewById<View>(R.id.country) as TextView
            name = itemView.findViewById<View>(R.id.name) as TextView
            city = itemView.findViewById<View>(R.id.city) as TextView
            iv = itemView.findViewById<View>(R.id.iv) as ImageView
        }

    }
}