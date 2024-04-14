package com.thechance.newspal.ui.features.Home

import com.thechance.newspal.ui.base.BaseUiEffect

sealed interface HomeUiEffect : BaseUiEffect {

    data class NavigateToArticleDetailsScreen(
        val articleTitle: String,
        val articleSource: String,
        val articlePublishedAt: String,
        val articleContent: String,
        val articleImageUrl: String
    ) : HomeUiEffect

    object NavigateToSearchScreen : HomeUiEffect
}
