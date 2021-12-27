package com.example.aniapp.model


import com.google.gson.annotations.SerializedName

data class UrlGif(
    @SerializedName("anime_name")
    val animeName: String,
    @SerializedName("url")
    val url: String
)