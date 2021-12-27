package com.example.aniapp.model


import com.google.gson.annotations.SerializedName

data class Url(
    @SerializedName("artist_href")
    val artistHref: String,
    @SerializedName("artist_name")
    val artistName: String,
    @SerializedName("source_url")
    val sourceUrl: String,
    @SerializedName("url")
    val url: String
)