package com.thechance.data.usecase

import com.thechance.data.entity.ArticleEntity

class GetArticlesFromDBUseCase(
    private val newsRepo: NewsRepo
) {
    suspend operator fun invoke(): List<ArticleEntity> {
        return newsRepo.getBookmarkedArticles()
    }
}