package com.thechance.data.usecase

import com.thechance.data.entity.ArticleEntity

class InsertArticleToDBUseCase(
    private val newsRepo: NewsRepo
) {
    suspend operator fun invoke(articleEntity: ArticleEntity) {
        newsRepo.saveArticle(articleEntity)
    }
}