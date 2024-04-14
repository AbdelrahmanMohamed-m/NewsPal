package com.thechance.data.local.entites

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticleDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    @Embedded val sourceDB: SourceDB,
    val title: String,
    val url: String,
    val urlToImage: String,
    val isBookmarked: Boolean
)

data class SourceDB(
    val sourceId: String,
    val name: String
)


