package com.example.complexjson

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_my_requests.*

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "http://167.172.46.142:1337/"
class MyRequests : AppCompatActivity() {
    lateinit var myAdapter: PestsAlertAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    val url3 = "http://167.172.46.142:1337/pest-alert-requests"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_requests)

        recyclerViewItemInStock.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerViewItemInStock.layoutManager = linearLayoutManager

        fetchRequests()
        getRequests()
    }

    private fun getRequests() {
        val queue = Volley.newRequestQueue(this)
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url3, null,
            { response ->
                val list: MutableList<PestAlertRequests> = ArrayList()
                for (i in 0 until response!!.length()) {
//                    val playerModel = PestAlertRequests()
                    val responseObj = response.getJSONObject(i)
                    val newdata = responseObj.getJSONArray("images")
                    for (j in 0 until newdata.length()) {
                        val imagedata = newdata.getJSONObject(j)
                        Log.i("MyData: ", imagedata.getString("url"))
                    }


//                    val id = responseObj.getInt("id")
//                    val name = responseObj.getString("name")
//                    val m = PestAlertRequests(name, id.toString())

//                    list.add(m)
                }

            }) { }
        queue.add(jsonArrayRequest)
    }


    private fun fetchRequests() {
        val okhttpHttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder().addInterceptor(
            okhttpHttpLoggingInterceptor
        )

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient.build())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getMyRequests().enqueue(object :
            Callback<List<PestAlertRequests>> {
            override fun onResponse(call: Call<List<PestAlertRequests>>, response: Response<List<PestAlertRequests>>) {
                if(response?.body() != null) {
                    Log.d("My Requests", response.body().toString())
                    myAdapter = PestsAlertAdapter(baseContext, response.body()!!)
                    recyclerViewItemInStock.adapter = myAdapter
                    myAdapter.notifyDataSetChanged()

                }
            }
            override fun onFailure(call: Call<List<PestAlertRequests>>, t: Throwable) {
                Log.d("Pests Requests", "onFailure:" + t.message)
            }
        })
    }


}