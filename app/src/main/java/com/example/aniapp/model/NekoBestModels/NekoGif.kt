package com.example.aniapp.model.NekoBestModels


import com.google.gson.annotations.SerializedName

data class NekoGif(
    @SerializedName("results")
    val url: List<UrlGif>
)