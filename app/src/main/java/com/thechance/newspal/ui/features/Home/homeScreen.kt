package com.thechance.newspal.ui.features.Home

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.thechance.newspal.R
import com.thechance.newspal.theme.dimens
import com.thechance.newspal.ui.composables.NewsListItem
import com.thechance.newspal.ui.composables.NoInternetPlaceHolder
import com.thechance.newspal.ui.features.ArticleDetailsScreen.navigateToArticleDetailsScreen
import com.thechance.newspal.ui.features.Home.Composables.NewsPager
import com.thechance.newspal.ui.navigation.Screen
import kotlinx.coroutines.delay
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    NavController: NavController,
) {
    val viewModel = getViewModel<HomeViewModel>()
    val state by viewModel.state.collectAsState()
    val pagerState = rememberPagerState(pageCount = { state.news.size })

    val effect by viewModel.effect.collectAsState(initial = null)
    when (effect) {
        is HomeUiEffect.NavigateToArticleDetailsScreen -> NavController.navigateToArticleDetailsScreen(
            (effect as HomeUiEffect.NavigateToArticleDetailsScreen).articleTitle,
            (effect as HomeUiEffect.NavigateToArticleDetailsScreen).articleSource,
            (effect as HomeUiEffect.NavigateToArticleDetailsScreen).articlePublishedAt,
            (effect as HomeUiEffect.NavigateToArticleDetailsScreen).articleImageUrl,
            (effect as HomeUiEffect.NavigateToArticleDetailsScreen).articleContent,
        )

        HomeUiEffect.NavigateToSearchScreen -> NavController.navigate(Screen.SearchScreen.route)
        else -> {
        }
    }


    when (state.isError) {
        true -> {
            NoInternetPlaceHolder(
                onTryAgainClick = {
                    viewModel.onTryAgainClick()
                }
            )
        }

        false -> {
            LaunchedEffect(key1 = state.news.isNotEmpty()) {
                while (true) {
                    delay(3000)
                    pagerState.animateScrollToPage(page = (pagerState.currentPage + 1) % 5)
                }
            }
            HomeContent(
                state = state,
                listener = viewModel,
                pagerState = pagerState,
            )
        }
    }


}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    state: HomeUiState,
    listener: HomeListener,
    pagerState: PagerState,
) {

    val stringListWithOnClick = listOf(
        Pair("All") { listener.onAllClick() },
        Pair("Business") { listener.onBusinessClick() },
        Pair("Technology") { listener.onTechnologyClick() },
        Pair("Health") { listener.onHealthClick() },
        Pair("Science") { listener.onScienceClick() },
        Pair("Sports") { listener.onSportsClick() },
        Pair("Entertainment") { listener.onEntertainmentClick() }
    )
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(end = MaterialTheme.dimens.space16),
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .size(MaterialTheme.dimens.space64)
                    )
                },
                title = {
                    val text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.secondary)) {
                            append("News")
                        }
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                            append("Pal")
                        }
                    }
                    Text(text = text)
                },
                actions = {

                    Box(
                        Modifier
                            .size(48.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .background(MaterialTheme.colorScheme.background)
                            .clickable { listener.onSearchClick() }
                    ) {
                        Icon(

                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(MaterialTheme.dimens.space24),
                            painter = painterResource(id = R.drawable.rounded_magnifer),
                            contentDescription = "Search",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                })
        }
    ) {
        Column(

            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(MaterialTheme.dimens.space16)
        ) {
            Spacer(modifier = Modifier.height(56.dp))
            NewsPager(pagerState = pagerState, state = state)
            LazyRow(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.spacedBy(MaterialTheme.dimens.space16),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = MaterialTheme.dimens.space8,
                        horizontal = MaterialTheme.dimens.space8
                    )
            ) {
                items(stringListWithOnClick.size) {
                    Text(
                        text = stringListWithOnClick[it].first,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .clip(
                                MaterialTheme.shapes.large
                            )
                            .clickable { stringListWithOnClick[it].second.invoke() }
                    )
                }
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.space4),
            ) {
                items(state.news.size, key = { state.news[it].title }) {
                    AnimatedVisibility(visible = state.news.isNotEmpty()) {
                        NewsListItem(
                            urlImage = state.news[it].urlToImage,
                            title = state.news[it].title,
                            description = state.news[it].description,
                            date = state.news[it].month,
                            time = state.news[it].timeAgo,
                            onClick = {
                                listener.onNewsTileClick(state.news[it])
                            }
                        )
                    }
                }
            }
        }
    }
}


