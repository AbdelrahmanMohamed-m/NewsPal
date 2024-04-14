package com.thechance.newspal.ui.features.Home

interface HomeListener {
    fun onPagerClick(newsObject: String)
    fun onNewsTileClick(newsObject: ArticleUiState)
    fun onAllClick()
    fun onBusinessClick()
    fun onTechnologyClick()
    fun onHealthClick()
    fun onScienceClick()
    fun onSportsClick()
    fun onEntertainmentClick()
    fun onSearchClick()
    fun onTryAgainClick()
}