package com.thechance.data.usecase

import com.thechance.data.entity.ArticleEntity

class HeadLinesUseCase(
    private val newsRepo: NewsRepo
) {
    suspend operator fun invoke(): List<ArticleEntity> {
        return newsRepo.getTopHeadlines(
            "us",
            10
        )
            .filterNot { it.urlToImage.isEmpty() || it.description.isEmpty() && it.title.isEmpty() }
    }
}