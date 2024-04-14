package com.thechance.newspal.ui.features.Home

import com.thechance.data.usecase.GetNewsUseCase
import com.thechance.data.util.ErrorHandler
import com.thechance.newspal.ui.base.BaseViewModel
import kotlinx.coroutines.flow.update


class HomeViewModel(
    private val getNewsUseCase: GetNewsUseCase,
) : BaseViewModel<HomeUiState, HomeUiEffect>(HomeUiState()), HomeListener {

    override val TAG: String = this::class.java.simpleName

    init {
        getNews()
    }


    private fun getNews() {
        tryToExecute(
            { getNewsUseCase(state.value.query).map { it.toArticleUiState() } },
            ::newsSuccess,
            ::newsError
        )
    }

    private fun newsSuccess(news: List<ArticleUiState>) {
        _state.value = HomeUiState(
            isLoading = false,
            news = news
        )

    }

    private fun newsError(error: ErrorHandler) {
        _state.update { it.copy(isLoading = false) }
        if (error is ErrorHandler.NoConnection) {
            _state.update { it.copy(isLoading = false, isError = true) }
        }
    }


    override fun onPagerClick(newsObject: String) {
        TODO("Not yet implemented")
    }

    override fun onNewsTileClick(newsObject: ArticleUiState) {
        effectActionExecutor(
            _effect,
            HomeUiEffect.NavigateToArticleDetailsScreen(
                newsObject.title,
                newsObject.source.id,
                newsObject.publishedAt,
                newsObject.content,
                newsObject.urlToImage
            )
        )
    }

    override fun onAllClick() {
        categoryClick(NewsType.ALL.type)
    }

    override fun onBusinessClick() {
        categoryClick(NewsType.BUSINESS.type)
    }

    override fun onTechnologyClick() {
        categoryClick(NewsType.TECHNOLOGY.type)

    }

    override fun onHealthClick() {
        categoryClick(NewsType.HEALTH.type)
    }

    override fun onScienceClick() {
        categoryClick(NewsType.SCIENCE.type)
    }

    override fun onSportsClick() {
        categoryClick(NewsType.SPORTS.type)
    }

    override fun onEntertainmentClick() {
        categoryClick(NewsType.ENTERTAINMENT.type)
    }

    override fun onSearchClick() {
        effectActionExecutor(_effect, HomeUiEffect.NavigateToSearchScreen)
    }

    override fun onTryAgainClick() {
        getNews()
    }

    private fun categoryClick(category: String) {
        _state.update { it.copy(query = category) }
        when (category) {
            NewsType.ALL.type -> getNews()
            NewsType.BUSINESS.type -> getNews()
            NewsType.TECHNOLOGY.type -> getNews()
            NewsType.HEALTH.type -> getNews()
            NewsType.SCIENCE.type -> getNews()
            NewsType.SPORTS.type -> getNews()
            NewsType.ENTERTAINMENT.type -> getNews()
        }
    }

}


