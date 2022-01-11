package com.example.aniapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aniapp.model.NekoBestModels.Url

@Dao
interface UrlDao {

    @Query("SELECT * FROM url")
    suspend fun getAll(): List<Url>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<Url>)
}