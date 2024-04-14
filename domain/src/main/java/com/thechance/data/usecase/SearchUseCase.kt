package com.thechance.data.usecase

import com.thechance.data.entity.ArticleEntity

class SearchUseCase(private val newsRepo: NewsRepo) {
    suspend operator fun invoke(query: String, sortBy: String): List<ArticleEntity> {
        return newsRepo.searchNews(query, sortBy, 10)
            .filterNot { it.urlToImage.isEmpty() || it.description.isEmpty() && it.title.isEmpty() }

    }
}