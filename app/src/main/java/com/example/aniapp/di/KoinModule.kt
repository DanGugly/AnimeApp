package com.example.aniapp.di

import com.example.aniapp.rest.NekosBestAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    fun okHttpClient(loggingInterceptor : HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    fun provideNekoBestAPI(okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(NekosBestAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(NekosBestAPI::class.java)

    single { provideLoggingInterceptor() }
    single { okHttpClient(get()) }
    single { provideNekoBestAPI(get()) }
}

val appModule = module {

}

val viewModelModule = module {
    // viewModel {}
}