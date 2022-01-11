package com.example.aniapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.aniapp.model.NekoBestModels.Url

@Database(entities = [Url::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun urlDao(): UrlDao

    companion object DatabaseBuilder {

        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = buildRoomDB(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildRoomDB(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "nekos"
            ).build()
    }
}