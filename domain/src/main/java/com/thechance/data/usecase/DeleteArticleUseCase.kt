package com.thechance.data.usecase

class DeleteArticleUseCase(
    private val newsRepo: NewsRepo
) {
    suspend operator fun invoke(title:String) {
        newsRepo.deleteArticle(title)
    }
}