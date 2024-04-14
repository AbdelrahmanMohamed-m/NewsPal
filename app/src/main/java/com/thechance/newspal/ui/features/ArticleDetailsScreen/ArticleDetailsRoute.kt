package com.thechance.newspal.ui.features.ArticleDetailsScreen

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.thechance.newspal.ui.navigation.Screen
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

private val ROUTE = Screen.ArticleDetailsScreen.route

fun NavController.navigateToArticleDetailsScreen(
    articleTitle: String,
    articleId: String,
    articlePublishedAt: String,
    articleUrlToImage: String,
    articleContent: String
) {
    val encodedUrl = URLEncoder.encode(articleUrlToImage, StandardCharsets.UTF_8.toString())
    val articleContentEncoded = URLDecoder.decode(articleContent, StandardCharsets.UTF_8.toString())

    navigate("${ROUTE}/${articleTitle}/${articleId}/${articlePublishedAt}/${encodedUrl}/${articleContentEncoded}")
}

fun NavGraphBuilder.articleDetailsRoute(
    navController: NavController
) {
    composable(
        route = "${ROUTE}/{${ArticleArgs.ARTICLE_TITLE}}/{${ArticleArgs.ARTICLE_ID}}/{${ArticleArgs.ARTICLE_PUBLISHED_AT}}/{${ArticleArgs.ARTICLE_URL_TO_IMAGE}}/{${ArticleArgs.ARTICLE_CONTENT}}",
        arguments = listOf(
            navArgument(name = ArticleArgs.ARTICLE_TITLE) {
                NavType.StringType
            },
            navArgument(name = ArticleArgs.ARTICLE_ID) {
                NavType.StringType
            },
            navArgument(name = ArticleArgs.ARTICLE_PUBLISHED_AT) {
                NavType.StringType
            },
            navArgument(name = ArticleArgs.ARTICLE_URL_TO_IMAGE) {
                NavType.StringType
            },
            navArgument(name = ArticleArgs.ARTICLE_CONTENT) {
                NavType.StringType
            }
        )
    ) {
        ArticleDetailsScreen(
            navController = navController,
        )
    }
}



class ArticleArgs(savedStateHandle: SavedStateHandle) {
    val articleTitle: String = checkNotNull(savedStateHandle[ARTICLE_TITLE])
    val articleId: String = checkNotNull(savedStateHandle[ARTICLE_ID])
    val articlePublishedAt: String = checkNotNull(savedStateHandle[ARTICLE_PUBLISHED_AT])
    val articleUrlToImage: String = checkNotNull(savedStateHandle[ARTICLE_URL_TO_IMAGE])
    val articleContent: String = checkNotNull(savedStateHandle[ARTICLE_CONTENT])

    companion object {
        const val ARTICLE_TITLE = "title"
        const val ARTICLE_ID = "id"
        const val ARTICLE_PUBLISHED_AT = "publishedAt"
        const val ARTICLE_URL_TO_IMAGE = "urlToImage"
        const val ARTICLE_CONTENT = "content"


    }
}