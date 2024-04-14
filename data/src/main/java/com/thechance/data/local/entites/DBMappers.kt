package com.thechance.data.local.entites

import com.thechance.data.entity.ArticleEntity
import com.thechance.data.entity.SourceEntity


fun ArticleDB.toEntity(): ArticleEntity {
    return ArticleEntity(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = sourceDB.toEntity(),
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}

fun SourceDB.toEntity(): SourceEntity {
    return SourceEntity(
        id = sourceId,
        name = name
    )
}

fun ArticleEntity.toDB(): ArticleDB {
    return ArticleDB(
        id = 0,
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        sourceDB = source.toDB(),
        title = title,
        url = url,
        urlToImage = urlToImage,
        isBookmarked = false
    )
}

fun SourceEntity.toDB(): SourceDB {
    return SourceDB(
        sourceId = id,
        name = name
    )
}

