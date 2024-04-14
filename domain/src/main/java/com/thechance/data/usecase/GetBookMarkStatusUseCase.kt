package com.thechance.data.usecase

class GetBookmarkedStatusUseCase(
    private val newsRepo: NewsRepo
) {
    suspend operator fun invoke(title: String): String {
        return newsRepo.getArticleByTitle(title)
    }
}