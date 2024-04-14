package com.thechance.newspal.ui.features.Search

import androidx.lifecycle.viewModelScope
import com.thechance.data.usecase.SearchUseCase
import com.thechance.newspal.ui.base.BaseViewModel
import com.thechance.data.util.ErrorHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewmodel(
    val searchUseCase: SearchUseCase
) : BaseViewModel<SearchUiState, SearchEffect>(SearchUiState()), SearchListener {
    override val TAG: String = this::class.java.simpleName
    private val actionStream = MutableSharedFlow<String>()

    init {
        observeKeyword()
    }


    private fun observeKeyword() {
        viewModelScope.launch(Dispatchers.Unconfined) {
            actionStream.debounce(1000).distinctUntilChanged().filter { keyword ->
                keyword.isNotBlank()
            }.collect {
                getNews()
            }
        }
    }

    private fun getNews() {
        val currentState = state.value
        _state.value = currentState.copy(isSearchLoading = true)
        tryToExecute(
            {
                searchUseCase(
                    currentState.searchQuery,
                    currentState.sortBy
                ).map { it.toSearchUiState() }
            },
            ::newsSuccess,
            ::newsError
        )
    }

    private fun newsSuccess(news: List<SearchUiState>) {
        _state.value = SearchUiState(
            isSearchLoading = false,
            searchResults = news,
            searchQuery = state.value.searchQuery,
            sortBy = state.value.sortBy,
            onClickAdvancedSearch = state.value.onClickAdvancedSearch
        )

    }

    private fun newsError(error: ErrorHandler) {
        _state.value = SearchUiState(
            isSearchLoading = false,
            isSearchError = true,
            searchError = error.toString(),
        )
    }


    fun onSearchTextChange(text: String) {
        _state.update { it.copy(searchQuery = text) }
        viewModelScope.launch { actionStream.emit(text) }
    }


    private fun filterSearch(sortOrder: String) {
        _state.update { it.copy(searchResults = emptyList()) }
        when (sortOrder) {
            SearchStates.PUBLISHED.state -> _state.update { it.copy(sortBy = SearchStates.PUBLISHED.state) }
            SearchStates.RELEVANT.state -> _state.update { it.copy(sortBy = SearchStates.RELEVANT.state) }
            SearchStates.POPULAR.state -> _state.update { it.copy(sortBy = SearchStates.POPULAR.state) }
        }
        _state.update { it.copy(sortBy = sortOrder) }
        if (state.value.searchQuery.isNotBlank()) {
            getNews()
        }

    }

    override fun onClickPublishedAt() {
        filterSearch(SearchStates.PUBLISHED.state)

    }

    override fun onClickRelevancy() {
        filterSearch(SearchStates.RELEVANT.state)
        log(state.value.sortBy)
    }

    override fun onClickPopularity() {
        filterSearch(SearchStates.POPULAR.state)
        log(state.value.sortBy)
    }

    override fun onClickNewsItem(productId: Long) {
        TODO("Not yet implemented")
    }

    override fun onClickAdvancedSearch() {
        _state.update { it.copy(onClickAdvancedSearch = !state.value.onClickAdvancedSearch) }
    }

    override fun onclickTryAgain() {
        TODO("Not yet implemented")
    }
}