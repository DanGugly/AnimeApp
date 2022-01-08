package com.example.aniapp.di

import com.example.aniapp.rest.NekosBestAPI
import com.example.aniapp.rest.NekosBestRepo
import com.example.aniapp.rest.NekosBestRepoImpl
import com.example.aniapp.viewmodel.NekoBestViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    fun provideNekoBestRepo(nekosBestAPI: NekosBestAPI) : NekosBestRepo = NekosBestRepoImpl(nekosBestAPI)

    fun provideGson() = GsonBuilder().create()

    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    fun okHttpClient(loggingInterceptor : HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    fun provideNekoBestAPI(okHttpClient: OkHttpClient, gson: Gson) =
        Retrofit.Builder()
            .baseUrl(NekosBestAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(NekosBestAPI::class.java)

    single { provideGson() }
    single { provideLoggingInterceptor() }
    single { okHttpClient(get()) }
    single { provideNekoBestAPI(get(), get()) }
    single { provideNekoBestRepo(get()) }
}

val appModule = module {

}

val viewModelModule = module {
    viewModel {
        NekoBestViewModel(get())
    }
}