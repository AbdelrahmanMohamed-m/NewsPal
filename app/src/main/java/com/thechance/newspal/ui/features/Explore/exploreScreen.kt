package com.thechance.newspal.ui.features.Explore

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.thechance.newspal.ui.composables.ExploreItem
import com.thechance.newspal.ui.composables.NoInternetPlaceHolder
import com.thechance.newspal.ui.composables.PlaceHolder
import com.thechance.newspal.ui.features.ArticleDetailsScreen.navigateToArticleDetailsScreen
import org.koin.androidx.compose.getViewModel


@Preview
@Composable
fun ExploreScreen(
    navController: NavController
) {
    val viewModel = getViewModel<ExploreViewModel>()
    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    when (effect) {
        is ExploreEffect.NavigateToArticleDetails -> navController.navigateToArticleDetailsScreen(
            articleId = (effect as ExploreEffect.NavigateToArticleDetails).articleId,
            articleTitle = (effect as ExploreEffect.NavigateToArticleDetails).articleTitle,
            articleContent = (effect as ExploreEffect.NavigateToArticleDetails).articleContent,
            articlePublishedAt = (effect as ExploreEffect.NavigateToArticleDetails).articlePublishedAt,
            articleUrlToImage = (effect as ExploreEffect.NavigateToArticleDetails).articleUrlToImage

        )

        else -> {}
    }

    AnimatedVisibility(visible = !state.isError) {
        ExploreContent(state = state, listener = viewModel)
    }
    AnimatedVisibility(visible = state.isError) {
        NoInternetPlaceHolder(
            onTryAgainClick = {
                viewModel.onTryAgainClick()
            }
        )
    }
    AnimatedVisibility(visible = state.latestNewUistates.isEmpty() && state.trendingNews.isEmpty() && state.relevantNews.isEmpty()) {
        PlaceHolder()
    }
}

@Composable
private fun ExploreContent(
    state: ExploreUiState,
    listener: ExploreListener
) {
    LazyColumn(
        Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(start = 16.dp, end = 0.dp, top = 16.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {

        item {
            ExploreColumn(
                size = state.latestNewUistates.size,
                text = "latest",
                state = state,
                listener = listener

            )
        }
        item {
            ExploreColumn(
                size = state.trendingNews.size,
                text = "Trending",
                state = state,
                listener = listener
            )
        }
        item {
            ExploreColumn(
                size = state.relevantNews.size,
                text = "Relevant News",
                state = state,
                listener = listener

            )
        }

    }
}


@Composable
fun ExploreColumn(
    size: Int,
    text: String,
    state: ExploreUiState,
    listener: ExploreListener,
) {

    Column {
        Text(text = text, style = MaterialTheme.typography.displaySmall)
        LazyRow(
            modifier = Modifier.padding(top = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(size) {
                when (text) {
                    "latest" -> ExploreItem(
                        imageUrl = state.latestNewUistates[it].urlToImage,
                        title = state.latestNewUistates[it].title,
                        description = state.latestNewUistates[it].description,
                        onclick = {
                            listener.onLatestClick(state.latestNewUistates[it])
                        }
                    )

                    "Trending" -> ExploreItem(
                        imageUrl = state.trendingNews[it].urlToImage,
                        title = state.trendingNews[it].title,
                        description = state.trendingNews[it].description,
                        onclick = {
                            listener.onTrendingNewsClick(state.trendingNews[it])
                        }
                    )

                    "Relevant News" -> ExploreItem(
                        imageUrl = state.relevantNews[it].urlToImage,
                        title = state.relevantNews[it].title,
                        description = state.relevantNews[it].description,
                        onclick = {
                            listener.onRelevantNewsClick(state.relevantNews[it])
                        }
                    )
                }
            }
        }

    }
}