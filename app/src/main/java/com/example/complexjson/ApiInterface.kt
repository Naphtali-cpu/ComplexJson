package com.example.complexjson

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("pest-alert-requests")
    fun getMyRequests(): Call<List<PestAlertRequests>>
}