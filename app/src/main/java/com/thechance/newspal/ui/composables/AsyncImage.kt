package com.thechance.newspal.ui.composables
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.thechance.newspal.R

@Composable
fun ImageNetwork(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentDescription: String = "",
    contentScale: ContentScale = ContentScale.Crop,
    colorFilter: ColorFilter? = null
) {

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        error = painterResource(R.drawable.no_images),
        colorFilter = colorFilter,
        placeholder = painterResource(R.drawable.no_images),
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier.background(MaterialTheme.colorScheme.onTertiary),
    )
}