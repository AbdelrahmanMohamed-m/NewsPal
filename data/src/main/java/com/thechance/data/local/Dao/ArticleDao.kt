package com.thechance.data.local.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thechance.data.local.entites.ArticleDB

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: ArticleDB)

    @Query("SELECT * FROM ArticleDB")
    suspend fun getAllArticles(): List<ArticleDB>

    @Query("DELETE FROM ArticleDB WHERE title = :title")
    suspend fun deleteArticle(title: String)

    @Query("SELECT title FROM ArticleDB WHERE title = :title LIMIT 1")
    suspend fun getArticleByTitle(title: String): String?
}