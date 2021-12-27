package com.example.aniapp.model


import com.google.gson.annotations.SerializedName

data class Nekos(
    @SerializedName("url")
    val url: List<Url>
)