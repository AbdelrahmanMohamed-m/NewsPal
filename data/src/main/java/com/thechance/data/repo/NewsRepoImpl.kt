package com.thechance.data.repo

import com.thechance.data.entity.ArticleEntity
import com.thechance.data.local.Dao.ArticleDao
import com.thechance.data.local.entites.toDB
import com.thechance.data.local.entites.toEntity
import com.thechance.data.remote.mapper.toEntity
import com.thechance.data.remote.model.NewsResponse
import com.thechance.data.remote.source.NewsService
import com.thechance.data.usecase.NewsRepo
import com.thechance.data.util.AlreadyExistException
import com.thechance.data.util.ForbiddenException
import com.thechance.data.util.InternalServerException
import com.thechance.data.util.InvalidDataException
import com.thechance.data.util.NotFoundException
import com.thechance.data.util.UnAuthorizedException
import retrofit2.Response

class NewsRepoImpl(
    private val newsService: NewsService,
    private val articleDao: ArticleDao
) : NewsRepo {
    override suspend fun getNews(
        query: String,
        pageSize: Int
    ): List<ArticleEntity> {
        return wrap {
            newsService.getAllNews(query = query, pageSize = pageSize)
        }.map { it.toEntity() }

    }


    override suspend fun searchNews(
        query: String,
        sortBy: String,
        pageSize: Int,
    ): List<ArticleEntity> {
        return wrap {
            newsService.searchNews(query = query, sortBy = sortBy, pageSize = pageSize)
        }.map { it.toEntity() }

    }

    override suspend fun getTopHeadlines(country: String, pageSize: Int): List<ArticleEntity> {
        return wrap {
            newsService.getTopHeadlines(
                country = country,
                pageSize = pageSize
            )
        }.map { it.toEntity() }
    }

    override suspend fun getBookmarkedArticles(): List<ArticleEntity> {
        return articleDao.getAllArticles().map { it.toEntity() }
    }

    override suspend fun saveArticle(articleEntity: ArticleEntity) {
        val articleDB = articleEntity.toDB()
        articleDao.insertArticle(articleDB)
    }

    override suspend fun deleteArticle(title: String) {
        articleDao.deleteArticle(title)
    }

    override suspend fun getArticleByTitle(title: String): String {
        return articleDao.getArticleByTitle(title) ?: ""
    }


    private suspend fun <T> wrap(function: suspend () -> Response<NewsResponse<T>>): T {
        val response = function()
        return if (response.isSuccessful) {
            val newsResponse = response.body()
            if (newsResponse?.status == "ok") {
                newsResponse.articles as T
            } else {
                when (response.code()) {
                    400 -> throw InvalidDataException()
                    403 -> throw ForbiddenException()
                    404 -> throw NotFoundException()
                    409 -> throw AlreadyExistException()
                    500 -> throw InternalServerException()
                    else -> throw Exception(newsResponse?.message ?: "Unknown error")
                }
            }
        } else {
            when (response.code()) {
                401 -> throw UnAuthorizedException()
                else -> throw Exception(response.message())
            }
        }
    }
}