package com.thechance.newspal.ui.features.Search

import CustomChip
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.thechance.newspal.R
import com.thechance.newspal.ui.composables.NewsListItem
import com.thechance.newspal.ui.navigation.Screen
import org.koin.androidx.compose.getViewModel

@Composable
fun SearchScreen(
    NavController: NavController,
) {
    val viewModel = getViewModel<SearchViewmodel>()
    val state by viewModel.state.collectAsState()
    SearchSucsessScreen(
        onSearchTextChange = viewModel::onSearchTextChange,
        state = state,
        onClick = {
            NavController.navigate(Screen.SearchScreen.route)
        },
        listener = viewModel,

        )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun SearchSucsessScreen(
    listener: SearchListener,
    onSearchTextChange: (String) -> Unit,
    state: SearchUiState,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 8.dp)

    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            TextField(
                modifier = Modifier
                    .weight(1f)
                    .clip(MaterialTheme.shapes.medium),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.onTertiary,
                    disabledTextColor = MaterialTheme.colorScheme.onSurface,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                value = state.searchQuery,
                onValueChange = onSearchTextChange,
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.searchsearchicon),
                        contentDescription = "serach icon",
                        tint = MaterialTheme.colorScheme.secondary

                    )
                },
            )
            Box(
                Modifier
                    .size(48.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.onTertiary)
                    .clickable {
                        listener.onClickAdvancedSearch()
                    }
            ) {
                Icon(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.advancedsearchicon),
                    contentDescription = "advancedSearchIcon",
                    tint = Color.Black,

                    )
            }

        }
        AnimatedVisibility(visible = state.onClickAdvancedSearch) {
            ChipRow(
                state = state,
                onclickNewest = { listener.onClickPublishedAt() },
                onclickRelevant = { listener.onClickRelevancy() },
                onclickPopular = { listener.onClickPopularity() },
            )
        }
        LazyColumn(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(state.searchResults.size) {
                NewsListItem(
                    urlImage = state.searchResults[it].urlToImage,
                    title = state.searchResults[it].title,
                    description = state.searchResults[it].description,
                    date = state.searchResults[it].monthOfPublishing,
                    time = state.searchResults[it].timeAgo,
                    onClick = onClick,
                )
            }
        }
    }
}


@Composable
fun ChipRow(
    state: SearchUiState,
    onclickNewest: () -> Unit,
    onclickRelevant: () -> Unit,
    onclickPopular: () -> Unit,
) {
    Row(
        modifier = Modifier.padding(all = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomChip(
            modifier = Modifier.size(100.dp, 32.dp),
            isSelected = state.published(),
            text = "New",
            onClick = { onclickNewest() },

            )
        CustomChip(
            modifier = Modifier.size(100.dp, 32.dp),
            isSelected = state.relevant(),
            text = "Relevant",
            onClick = { onclickRelevant() }
        )
        CustomChip(
            modifier = Modifier.size(100.dp, 32.dp),
            isSelected = state.popular(),
            text = "popular",
            onClick = { onclickPopular() }
        )
    }
}