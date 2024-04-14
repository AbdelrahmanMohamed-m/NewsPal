package com.thechance.data.usecase

import com.thechance.data.entity.ArticleEntity

interface NewsRepo {
    suspend fun getNews(
        query: String,
        pageSize: Int
    ): List<ArticleEntity>

    suspend fun searchNews(
        query: String,
        sortBy: String,
        pageSize: Int,
    ): List<ArticleEntity>

    suspend fun getTopHeadlines(
        country: String,
        pageSize: Int
    ): List<ArticleEntity>

    suspend fun getBookmarkedArticles(): List<ArticleEntity>
    suspend fun saveArticle(articleEntity: ArticleEntity)
    suspend fun deleteArticle(title: String)
    suspend fun getArticleByTitle(title: String): String
}