package com.thechance.newspal.ui.features.ArticleDetailsScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.thechance.data.usecase.DeleteArticleUseCase
import com.thechance.data.usecase.GetBookmarkedStatusUseCase
import com.thechance.data.usecase.InsertArticleToDBUseCase
import com.thechance.newspal.ui.base.BaseViewModel
import com.thechance.newspal.ui.util.toArticleEntity
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ArticleDetailsViewmodel(
    savedStateHandle: SavedStateHandle,
    val deleteBookMarkedUseCase: DeleteArticleUseCase,
    val getBookMarkStatusUseCase: GetBookmarkedStatusUseCase,
    val insertArticleToDBUseCase: InsertArticleToDBUseCase,
) : BaseViewModel<ArticleDetailsUiState, ArticleDetailsUiEffect>(ArticleDetailsUiState()),
    ArticleDetailsListener {
    override val TAG: String = javaClass.simpleName

    val args = ArticleArgs(savedStateHandle)

    init {
      initializeViewState()
    }


    fun hello() {
        println("Hello")
    }
    private fun initializeViewState() {
        _state.update {
            it.copy(
                title = args.articleTitle,
                content = args.articleContent,
                sourceId = args.articleId,
                publishedAt = args.articlePublishedAt,
                imageUrl = args.articleUrlToImage,
                isBookmarked = getBookMarkedStatus(),
            )
        }
    }
    private fun getBookMarkedStatus(): Boolean {
        viewModelScope.launch {
            val isBookmarked = getBookMarkStatusUseCase(args.articleTitle)
            _state.update {
                it.copy(isBookmarked = isBookmarked.isNotEmpty())
            }
        }
        return state.value.isBookmarked
    }

    override fun onClickBookmarked() {

        viewModelScope.launch {
            if (state.value.isBookmarked.not()) {
                _state.update {
                    it.copy(isBookmarked = !state.value.isBookmarked)
                }
                insertArticleToDBUseCase(state.value.toArticleEntity())
            } else {
                _state.update {
                    it.copy(isBookmarked = !state.value.isBookmarked)
                }
                deleteBookMarkedUseCase(state.value.title)
            }
        }
    }
}
