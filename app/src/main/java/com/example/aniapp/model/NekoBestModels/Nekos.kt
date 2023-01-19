package com.example.aniapp.model.NekoBestModels


import com.google.gson.annotations.SerializedName

data class Nekos(
    @SerializedName("results")
    val url: List<Url>
)