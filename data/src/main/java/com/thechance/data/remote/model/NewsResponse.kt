package com.thechance.data.remote.model

data class NewsResponse<T>(
    val articles: List<Article>?,
    val status: String?,
    val totalResults: Int?,
    val message: String?
)