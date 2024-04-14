package com.thechance.newspal.ui.features.Home

import com.thechance.data.entity.ArticleEntity
import com.thechance.data.entity.SourceEntity
import com.thechance.newspal.ui.util.formatMonth
import com.thechance.newspal.ui.util.formatTimeAgo

data class HomeUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val error: String = "",
    val news: List<ArticleUiState> = emptyList(),
    val query: String = NewsType.ALL.type
)

data class SourceUiState(
    val id: String,
    val name: String
)

data class ArticleUiState(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: SourceUiState,
    val title: String,
    val url: String,
    val urlToImage: String
) {
    val timeAgo: String
        get() = formatTimeAgo(publishedAt)

    val month: String
        get() = formatMonth(publishedAt)
}

fun ArticleEntity.toArticleUiState(): ArticleUiState {
    return ArticleUiState(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = source.toSourceUiState(),
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}

fun SourceEntity.toSourceUiState(): SourceUiState {
    return SourceUiState(
        id = id,
        name = name
    )
}

enum class NewsType(val type: String) {
    ALL("All"),
    BUSINESS("Business"),
    TECHNOLOGY("Technology"),
    HEALTH("Health"),
    SCIENCE("Science"),
    SPORTS("Sports"),
    ENTERTAINMENT("Entertainment")
}