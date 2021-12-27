package com.example.aniapp.model


import com.google.gson.annotations.SerializedName

data class NekoGif(
    @SerializedName("url")
    val url: List<UrlGif>
)