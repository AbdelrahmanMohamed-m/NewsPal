package com.thechance.newspal.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thechance.newspal.R
import com.thechance.newspal.theme.Shapes
import com.thechance.newspal.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        enabled = true,
        value = query,
        onValueChange = onValueChange,
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),

        shape = Shapes.medium,
        textStyle = MaterialTheme.typography.displaySmall,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            disabledTextColor = MaterialTheme.colorScheme.tertiaryContainer,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        leadingIcon = {
            Icon(
                modifier = Modifier
                    .size(24.dp),
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground
            )
        },
        placeholder = {
            Text(
                text = "Select A country",
                style = Typography.labelMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    )
}


@Preview
@Composable
fun SearchBarPreview() {
    SearchBar(
        query = "",
        onValueChange = {}
    )
}
