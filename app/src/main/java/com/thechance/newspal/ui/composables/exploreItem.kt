package com.thechance.newspal.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@Composable
fun ExploreItem(
    imageUrl: String,
    title: String,
    description: String,
    onclick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(width = 220.dp, height = 223.dp)
    ) {
        Column {
            ImageNetwork(
                imageUrl = imageUrl,
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .clickable { onclick() }
                    .size(220.dp, 120.dp)

            )
            Text(
                text = title,
                maxLines = 1,
                style = MaterialTheme.typography.bodyMedium,

                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = description,
                maxLines = 2,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),

                )
        }
    }
}