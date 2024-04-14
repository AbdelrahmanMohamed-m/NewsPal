package com.thechance.newspal.ui.features.CountryPicker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thechance.newspal.theme.dimens
import com.thechance.newspal.ui.composables.CountryItem
import com.thechance.newspal.ui.composables.SearchBar


// available languages to chose from ar de en es fr he it nl no pt ru sv ud zh
@Preview
@Composable
fun CountryPicker() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Select Your country",
            color = Color.Black,
            modifier = Modifier.padding(MaterialTheme.dimens.space16),
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
        )
        SearchBar(
            query = "",
            onValueChange = {},
            modifier = Modifier.padding(16.dp)
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f) // Take all available space
                .fillMaxSize()
                .padding(16.dp)
        )
        {
            items(20) {
                CountryItem(countryName = "Palestine")
            }
        }

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 16.dp)
                .size(height = 50.dp, width = 328.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            Text(text = "Confirm")
        }
    }
}

