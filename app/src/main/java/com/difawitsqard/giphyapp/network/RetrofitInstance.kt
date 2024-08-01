package com.difawitsqard.giphyapp.network

// Created : 01/08/24
// NIM     : 10121916
// Nama    : Difa Witsqa RD
// Kelas   : IF9K

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.giphy.com/v1/"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiService::class.java)
}