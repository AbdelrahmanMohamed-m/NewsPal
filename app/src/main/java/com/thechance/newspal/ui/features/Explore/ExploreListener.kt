package com.thechance.newspal.ui.features.Explore

interface ExploreListener {

    fun onTryAgainClick()
    fun  onLatestClick(category: LatestNewsUiState)
    fun onTrendingNewsClick(trendingNews: TrendingUiState)
    fun onRelevantNewsClick(relevantNews: RelevantUiState)

}