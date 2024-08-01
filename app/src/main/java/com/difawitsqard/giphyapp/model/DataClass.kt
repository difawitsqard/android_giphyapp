package com.difawitsqard.giphyapp.model

// Created : 01/08/24
// NIM     : 10121916
// Nama    : Difa Witsqa RD
// Kelas   : IF9K

import com.google.gson.annotations.SerializedName

data class DataResult(
    @SerializedName("data") val res: List<DataObject>
)

data class DataObject(
    @SerializedName("images") val images: DataImage,
    @SerializedName("title") val title: String,
    @SerializedName("source_tld") val source_tld: String
)

data class DataImage(
    @SerializedName("original") val ogImage: ogImage
)

data class ogImage(
    val url: String
)