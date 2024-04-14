package com.thechance.newspal.ui.features.Explore

import com.thechance.data.usecase.HeadLinesUseCase
import com.thechance.data.usecase.SearchUseCase
import com.thechance.data.util.ErrorHandler
import com.thechance.newspal.ui.base.BaseViewModel
import kotlinx.coroutines.flow.update

class ExploreViewModel(
    private val topHeadlines: HeadLinesUseCase,
    private val trendingNews: SearchUseCase
) : BaseViewModel<ExploreUiState, ExploreEffect>(ExploreUiState()),
    ExploreListener {
    override val TAG: String = this::class.java.simpleName


    init {
        getTopHeadlines()
        getTrendingNews()
        getRelevantNews()
    }


    private fun getTopHeadlines() {
        tryToExecute(
            { topHeadlines().map { it.toLatestNews() } },
            ::topHeadlinesSuccess,
            ::topHeadlinesError
        )
    }

    private fun topHeadlinesSuccess(news: List<LatestNewsUiState>) {
        _state.value = state.value.copy(
            isLoading = false,
            isError = false,
            latestNewUistates = news
        )
    }

    private fun topHeadlinesError(errorHandler: ErrorHandler) {
        _state.value = state.value.copy(
            isLoading = false,
            isError = true,
            error = errorHandler.toString()
        )
    }

    private fun getTrendingNews() {

        tryToExecute(
            {
                trendingNews(
                    "Trending",
                    "PublishedAt",
                ).map { it.toTrendingNews() }
            },
            ::trendingNewsSuccess,
            ::trendingNewsError
        )
    }

    private fun trendingNewsSuccess(news: List<TrendingUiState>) {
        _state.update { it.copy(isLoading = false, isError = false, trendingNews = news) }
    }

    private fun trendingNewsError(errorHandler: ErrorHandler) {
        _state.update {
            it.copy(
                isLoading = false,
                isError = true,
                error = errorHandler.toString()
            )
        }
    }

    private fun getRelevantNews() {
        tryToExecute(
            {
                trendingNews(
                    "Random",
                    "Relevant",
                ).map { it.toRelevantNews() }
            },
            ::relevantNewsSuccess,
            ::relevantNewsError
        )
    }

    private fun relevantNewsSuccess(news: List<RelevantUiState>) {
        _state.update { it.copy(isLoading = false, isError = false, relevantNews = news) }
    }

    private fun relevantNewsError(errorHandler: ErrorHandler) {
        _state.update {
            it.copy(
                isLoading = false,
                isError = true,
                error = errorHandler.toString()
            )
        }
    }

    override fun onTryAgainClick() {
        _state.update { it.copy(isLoading = true) }
        getTopHeadlines()
        getTrendingNews()
        getRelevantNews()
    }

    override fun onLatestClick(category: LatestNewsUiState) {
        effectActionExecutor(
            _effect,
            ExploreEffect.NavigateToArticleDetails(
                category.sourceID,
                category.title,
                category.content,
                category.publishedAt,
                category.urlToImage,
            )
        )
    }

    override fun onTrendingNewsClick(trendingNews: TrendingUiState) {
        effectActionExecutor(
            _effect,
            ExploreEffect.NavigateToArticleDetails(
                trendingNews.sourceID,
                trendingNews.title,
                trendingNews.content,
                trendingNews.publishedAt,
                trendingNews.urlToImage,
            )
        )
    }

    override fun onRelevantNewsClick(relevantNews: RelevantUiState) {
        effectActionExecutor(
            _effect,
            ExploreEffect.NavigateToArticleDetails(
                relevantNews.sourceID,
                relevantNews.title,
                relevantNews.content,
                relevantNews.publishedAt,
                relevantNews.urlToImage,
            )
        )
    }

}
