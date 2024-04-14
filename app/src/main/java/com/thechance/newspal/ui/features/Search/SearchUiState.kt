package com.thechance.newspal.ui.features.Search

import com.thechance.data.entity.ArticleEntity
import com.thechance.newspal.ui.util.formatMonth
import com.thechance.newspal.ui.util.formatTimeAgo


data class SearchUiState(
    val urlToImage: String = "",
    val title: String = "",
    val description: String = "",
    val publishedAt: String = "",
    val searchQuery: String = "",
    val sortBy: String = SearchStates.PUBLISHED.state,
    val searchResults: List<SearchUiState> = emptyList(),
    val isSearchLoading: Boolean = false,
    val isSearchError: Boolean = false,
    val searchError: String = "",
    val onClickAdvancedSearch: Boolean = false,
    val isBookmark: Boolean = false,
) {
    val timeAgo: String
        get() = formatTimeAgo(publishedAt)

    val monthOfPublishing: String
        get() = formatMonth(publishedAt)
}


fun ArticleEntity.toSearchUiState(): SearchUiState {
    return SearchUiState(
        urlToImage = urlToImage,
        title = title,
        description = description,
        publishedAt = publishedAt,
    )
}

enum class SearchStates(val state: String) {
    PUBLISHED("publishedAt"),
    RELEVANT("relevancy"),
    POPULAR("popularity"),
}

fun SearchUiState.published() = this.sortBy == SearchStates.PUBLISHED.state
fun SearchUiState.relevant() = this.sortBy == SearchStates.RELEVANT.state
fun SearchUiState.popular() = this.sortBy == SearchStates.POPULAR.state