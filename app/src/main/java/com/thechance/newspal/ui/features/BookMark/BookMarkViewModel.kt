package com.thechance.newspal.ui.features.BookMark

import androidx.lifecycle.viewModelScope
import com.thechance.data.usecase.GetArticlesFromDBUseCase
import com.thechance.newspal.ui.base.BaseViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookMarkViewModel(
    val getBookMarkedUseCase: GetArticlesFromDBUseCase,
) : BaseViewModel<BookMarkUiState, BookMarkedUiEffect>(BookMarkUiState()), BookMarkListener{

    override val TAG: String = javaClass.simpleName



     fun getBookMarkedArticles() {
        viewModelScope.launch {
            val articles = getBookMarkedUseCase()
            _state.update { bookMarkUiState ->
                bookMarkUiState.copy(articles = articles.map { it.toUiState() })
            }
        }
    }

    override fun onArticleClicked() {

    }
}