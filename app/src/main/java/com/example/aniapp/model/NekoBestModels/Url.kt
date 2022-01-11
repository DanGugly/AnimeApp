package com.example.aniapp.model.NekoBestModels


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Url(
    @SerializedName("artist_href")
    val artistHref: String,
    @SerializedName("artist_name")
    val artistName: String,
    @SerializedName("source_url")
    val sourceUrl: String,
    @PrimaryKey
    @SerializedName("url")
    val url: String
)