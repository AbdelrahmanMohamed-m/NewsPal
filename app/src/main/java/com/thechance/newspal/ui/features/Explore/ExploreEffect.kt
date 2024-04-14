package com.thechance.newspal.ui.features.Explore

import com.thechance.newspal.ui.base.BaseUiEffect


sealed interface ExploreEffect : BaseUiEffect {
    data class NavigateToArticleDetails(
        val articleId: String,
        val articleTitle: String,
        val articleContent: String,
        val articlePublishedAt: String,
        val articleUrlToImage: String
    ) : ExploreEffect
}