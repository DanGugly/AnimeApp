package com.example.aniapp

import android.app.Application
import com.example.aniapp.di.appModule
import com.example.aniapp.di.networkModule
import com.example.aniapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AnimeApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@AnimeApp)
            modules(listOf(networkModule, viewModelModule, appModule))
        }
    }
}