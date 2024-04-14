package com.thechance.data.usecase


import com.thechance.data.entity.ArticleEntity

class GetNewsUseCase(private val newsRepo: NewsRepo) {
    suspend operator fun invoke(query: String): List<ArticleEntity> {
        return newsRepo.getNews(query, 10)
            .filterNot { it.urlToImage.isEmpty() || it.description.isEmpty() && it.title.isEmpty() ||it.source.id.isEmpty() || it.publishedAt.isEmpty() || it.content.isEmpty()}
    }
}