package com.thechance.newspal.ui.features.Home.Composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.thechance.newspal.theme.dimens
import com.thechance.newspal.ui.composables.ImageNetwork
import com.thechance.newspal.ui.composables.horizontalPagerIndicator
import com.thechance.newspal.ui.features.Home.HomeUiState

@Composable
@OptIn(ExperimentalFoundationApi::class)
 fun NewsPager(
    pagerState: PagerState,
    state: HomeUiState
) {
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.height(164.dp),
    ) {
        if (state.news.isNotEmpty()) {
            ImageNetwork(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(MaterialTheme.dimens.space24))
                    .height(164.dp),
                imageUrl = state.news[it].urlToImage,
                contentDescription = "News Image"
            )
        }
    }
    horizontalPagerIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = MaterialTheme.dimens.space16),
        itemCount = if (state.news.size > 5) 5 else state.news.size,
        selectedPage = pagerState.currentPage,
    )
}