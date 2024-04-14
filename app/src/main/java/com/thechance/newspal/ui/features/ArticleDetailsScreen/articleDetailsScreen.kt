package com.thechance.newspal.ui.features.ArticleDetailsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.thechance.newspal.ui.composables.ImageNetwork
import org.koin.androidx.compose.getViewModel


@Composable
fun ArticleDetailsScreen(
    navController: NavController

) {
    val viewModel: ArticleDetailsViewmodel = getViewModel()
    val state by viewModel.state.collectAsState()
    ArticleDetailsContent(state = state, navController = navController, listener = viewModel)

}

@Composable
private fun ArticleDetailsContent(
    listener: ArticleDetailsListener,
    state: ArticleDetailsUiState,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)

    ) {
        TopBarIcons(
            onBookmarkClick = { listener.onClickBookmarked() },
            onBackClick = { navController.popBackStack() },
            isBookmarked = state.isBookmarked
        )
        Text(
            text = state.title,
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                color = Color.Black,
                fontSize = 24.sp
            )
        )
        Text(
            text = "${state.formatDate} by: ${state.sourceId}", style = TextStyle(
                color = Color.Gray,
                fontSize = 12.sp
            )
        )
        ImageNetwork(
            imageUrl = state.imageUrl,
            contentDescription = "Article Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(184.dp)
                .clip(shape = MaterialTheme.shapes.medium)
        )
        Text(
            text = state.content, style = MaterialTheme.typography.bodyMedium
        )
    }
}


@Composable
private fun TopBarIcons(
    onBackClick: () -> Unit,
    onBookmarkClick: () -> Unit,
    isBookmarked: Boolean
) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            Modifier.clickable { onBackClick() })
        Icon(
            painter = painterResource(id = getBookmarkIcon(isBookmarked)),
            contentDescription = "bookmark icon",
            Modifier.clickable { onBookmarkClick() }
        )

    }
}