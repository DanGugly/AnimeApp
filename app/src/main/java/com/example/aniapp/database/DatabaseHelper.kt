package com.example.aniapp.database

import com.example.aniapp.model.NekoBestModels.Url

/**
 * Create instance of db helper;
 * val dbHelper = DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))
 * then to use call;
 * val usersFromDb = dbHelper.getUsers()
 * in a try catch
 */
class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {

    override suspend fun getNekos(): List<Url> = appDatabase.urlDao().getAll()

    override suspend fun insertNekos(nekos: List<Url>) = appDatabase.urlDao().insertAll(nekos)

}
interface DatabaseHelper{
    suspend fun getNekos() : List<Url>
    suspend fun insertNekos(nekos: List<Url>)
}