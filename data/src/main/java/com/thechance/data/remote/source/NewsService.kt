package com.thechance.data.remote.source

import com.thechance.data.remote.model.Article
import com.thechance.data.remote.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("everything")
    suspend fun getAllNews(
        @Query("q") query: String,
        @Query("pageSize") pageSize: Int
    ): Response<NewsResponse<List<Article>>>


    @GET("everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("pageSize") pageSize: Int,
        @Query("sortBy") sortBy: String
    ): Response<NewsResponse<List<Article>>>


    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("pageSize") pageSize: Int
    ): Response<NewsResponse<List<Article>>>
}


