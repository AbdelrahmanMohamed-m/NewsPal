package com.thechance.newspal.ui.features.ArticleDetailsScreen

import com.thechance.newspal.R
import com.thechance.newspal.ui.util.formatDate


data class ArticleDetailsUiState(
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
}


fun getBookmarkIcon(isBookmarked: Boolean): Int {
    return if (isBookmarked) {
        R.drawable.bookmarkfoucsed
    } else {
        R.drawable.bookmark
    }
}