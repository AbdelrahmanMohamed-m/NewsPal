package com.thechance.newspal.ui.features.BookMark

import com.thechance.data.entity.ArticleEntity
import com.thechance.newspal.ui.util.formatDate
import com.thechance.newspal.ui.util.formatTimeAgo

data class BookMarkUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val articles: List<BookMarkUiState> = emptyList(),
    val title: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val url: String = "",
    val publishedAt: String = "",
    val content: String = "",
    val sourceId: String = "",
    val isBookmarked: Boolean = false
) {

    val formatDate: String
        get() = formatDate(publishedAt)
    val formatTime: String
        get() = formatTimeAgo(publishedAt)
}


fun ArticleEntity.toUiState() = BookMarkUiState(
    title = title,
    description = description,
    imageUrl = urlToImage,
    url = url,
    publishedAt = publishedAt,
    content = content,
    sourceId = source.id,
    isBookmarked = true
)