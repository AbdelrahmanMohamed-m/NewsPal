package com.thechance.newspal.ui.util

import com.thechance.data.entity.ArticleEntity
import com.thechance.data.entity.SourceEntity
import com.thechance.newspal.ui.features.ArticleDetailsScreen.ArticleDetailsUiState


fun ArticleDetailsUiState.toArticleEntity(): ArticleEntity {
    return ArticleEntity(
        author = "",
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = SourceEntity
            (
            id = sourceId,
            name = ""
        ),
        title = title,
        url = url,
        urlToImage = imageUrl
    )
}