package com.thechance.newspal.ui.features.BookMark

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thechance.newspal.ui.composables.NewsListItem
import com.thechance.newspal.ui.composables.PlaceHolder
import org.koin.androidx.compose.getViewModel


@Composable
fun BookMarkedScreen() {

    val viewModel = getViewModel<BookMarkViewModel>()
    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    LaunchedEffect(key1 = true) {
        viewModel.getBookMarkedArticles()
    }
    BookMarkedContent(
        state = state,
        listener = viewModel
    )
}

@Composable
private fun BookMarkedContent(
    state: BookMarkUiState,
    listener: BookMarkListener
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (state.articles.isEmpty()) {
            PlaceHolder()
        } else {
            Text(text = "Saved Articles :", style = MaterialTheme.typography.bodyLarge)
            LazyColumn(
                modifier = Modifier.padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(state.articles.size) {
                    NewsListItem(
                        onClick = {listener.onArticleClicked()},
                        urlImage = state.articles[it].imageUrl,
                        title = state.articles[it].title,
                        description = state.articles[it].description,
                        date = state.articles[it].formatDate,
                        time = state.articles[it].formatTime,
                    )
                }
            }
        }
    }
}
