package com.thechance.data.remote.mapper

import com.thechance.data.entity.ArticleEntity
import com.thechance.data.entity.SourceEntity
import com.thechance.data.remote.model.Article
import com.thechance.data.remote.model.Source


fun Article.toEntity(): ArticleEntity {
    return ArticleEntity(
        author = author ?: "",
        content = content ?: "",
        description = description ?: "",
        publishedAt = publishedAt ?: "",
        source = source?.toEntity() ?: SourceEntity(
            id = "",
            name = ""
        ),
        title = title ?: "",
        url = url ?: "",
        urlToImage = urlToImage ?: ""
    )
}

fun Source.toEntity(): SourceEntity {
    return SourceEntity(
        id = id ?: "",
        name = name ?: "",
    )
}