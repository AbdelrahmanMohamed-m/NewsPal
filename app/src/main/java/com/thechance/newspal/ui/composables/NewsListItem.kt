package com.thechance.newspal.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.thechance.newspal.R

@Composable
fun NewsListItem(
    modifier: Modifier = Modifier,
    urlImage: String,
    title: String,
    description: String,
    date: String,
    time: String,
    onClick: () -> Unit

) {
    Box(
        modifier = Modifier
            .clip(shape = MaterialTheme.shapes.medium)
            .background(color = Color.White)
            .fillMaxWidth()
            .height(93.dp)
            .clickable { onClick() }
    ) {
        Row {

            ImageNetwork(
                imageUrl = urlImage,
                contentDescription = "News Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(152.dp)
                    .padding(8.dp)
                    .clip(shape = MaterialTheme.shapes.medium)
            )
            Column(
                modifier = Modifier
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {

                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.bookmark),
                        contentDescription = "Bookmark Icon",
                        modifier = Modifier
                            .size(14.dp)
                    )
                }
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    modifier = Modifier.padding(top = 4.dp)

                )
                Row(
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Calendar Icon",
                            modifier = Modifier.size(14.dp)

                        )
                        Text(
                            text = date,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.clock),
                            contentDescription = "Second Icon",
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = time,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}
