package com.difawitsqard.giphyapp.network

// Created : 01/08/24
// NIM     : 10121916
// Nama    : Difa Witsqa RD
// Kelas   : IF9K

import com.difawitsqard.giphyapp.model.DataResult
import retrofit2.http.GET

interface ApiService {
    //https://api.giphy.com/v1/gifs/trending?api_key=YOUR
    //API KEY&limit=10&rating=g

    @GET("gifs/trending?api_key=BxEbcLbTmIbA37I816KFbyM8pjQMvpnH")
    fun getGifs(): retrofit2.Call<DataResult>
}