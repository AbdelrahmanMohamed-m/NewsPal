package com.thechance.newspal.ui.features.Search

interface SearchListener {
    fun onClickPublishedAt()
    fun onClickRelevancy()
    fun onClickPopularity()
    fun onClickNewsItem(productId: Long)
    fun onclickTryAgain()
    fun onClickAdvancedSearch()
}