package com.example.aniapp.rest

import com.example.aniapp.model.NekoGif
import com.example.aniapp.model.Nekos
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * https://docs.nekos.best/info/endpoints
 * Network API for first interface
 */
interface NekosBestAPI {

    @GET(NEKOS)
    suspend fun getNekos(
        @Query("amount") amt : Int = AMOUNT
    ) : Response<Nekos>

    @GET("{category}")
    suspend fun getGifs(
        @Path("category") category : String = GIF_CATEGORY,
        @Query("amount") amt : Int = AMOUNT
    ) : Response<NekoGif>

    companion object{
        var GIF_CATEGORY = ""
        const val BASE_URL = "https://nekos.best/api/v1/"
        private const val AMOUNT = 20
        private const val NEKOS = "nekos"
        private const val BAKA = "baka"
        private const val BITE = "bite"
        private const val BLUSH = "blush"
        private const val BORED = "bored"
        private const val CRY = "cry"
        private const val CUDDLE = "cuddle"
        private const val DANCE = "dance"
        private const val FACEPALM = "facepalm"
        private const val FEED = "feed"
        private const val HAPPY = "happy"
        private const val HIGHFIVE = "highfive"
        private const val HUG = "hug"
        private const val KISS = "kiss"
        private const val LAUGH = "laugh"
        private const val PAT = "pat"
        private const val POKE = "poke"
        private const val POUT = "pout"
        private const val SHRUG = "shrug"
        private const val SLAP = "slap"
        private const val SLEEP = "SLEEP"
        private const val SMILE = "smile"
        private const val SMUG = "smug"
        private const val STARE = "stare"
        private const val THINK = "think"
        private const val THUMBSUP = "thumbsup"
        private const val TICKLE = "tickle"
        private const val WAVE = "wave"
        private const val WINK = "wink"
    }
}