package com.thechance.data.local

import androidx.room.Database
import com.thechance.data.local.Dao.ArticleDao
import com.thechance.data.local.entites.ArticleDB


@Database(entities = [ArticleDB::class], version = 1, exportSchema = false)
abstract class NewsPalDataBase : androidx.room.RoomDatabase() {
    abstract val articleDao: ArticleDao
}