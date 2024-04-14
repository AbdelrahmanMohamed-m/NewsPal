package com.thechance.newspal.ui.features.Explore

import com.thechance.data.entity.ArticleEntity

data class ExploreUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val error: String = "",
    val trendingNews: List<TrendingUiState> = emptyList(),
    val relevantNews: List<RelevantUiState> = emptyList(),
    val latestNewUistates: List<LatestNewsUiState> = emptyList(),
)

data class TrendingUiState(
    val news: List<TrendingUiState> = emptyList(),
    val title: String = "",
    val description: String = "",
    val urlToImage: String = "",
    val publishedAt: String = "",
    val query: String = "",
    val sourceID: String = "",
    val content: String = "",
)

data class RelevantUiState(
    val news: List<RelevantUiState> = emptyList(),
    val title: String = "",
    val description: String = "",
    val urlToImage: String = "",
    val publishedAt: String = "",
    val sourceID: String = "",
    val content: String = "",
)

data class LatestNewsUiState(
    val news: List<LatestNewsUiState> = emptyList(),
    val title: String = "",
    val description: String = "",
    val urlToImage: String = "",
    val timeAgo: String = "",
    val sourceID: String = "",
    val content: String = "",
    val publishedAt: String = "",

    )


fun ArticleEntity.toLatestNews(): LatestNewsUiState {
    return LatestNewsUiState(
        title = title,
        description = description,
        urlToImage = urlToImage,
        timeAgo = publishedAt,
        sourceID = source.id,
        content = content,
        publishedAt = publishedAt
    )
}

fun ArticleEntity.toTrendingNews(): TrendingUiState {
    return TrendingUiState(
        title = title,
        description = description,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        query = source.name,
        sourceID = source.id,
        content = content
    )
}

fun ArticleEntity.toRelevantNews(): RelevantUiState {
    return RelevantUiState(
        title = title,
        description = description,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        sourceID = source.id,
        content = content
    )
}