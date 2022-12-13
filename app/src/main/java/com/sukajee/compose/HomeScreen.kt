package com.sukajee.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { HomeScreenItem(text = "Lists") {
            // handle what should happen when this is clicked

        } }
        item { HomeScreenItem(text = "Borders") {} }
        item { HomeScreenItem(text = "ArcProgress") {} }
        item { HomeScreenItem(text = "ColumnRows") {} }
        item { HomeScreenItem(text = "ConstraintLayout") {} }
        item { HomeScreenItem(text = "SimpleUI") {} }
        item { HomeScreenItem(text = "State") {} }
        item { HomeScreenItem(text = "TextFieldSnackbar") {} }
        item { HomeScreenItem(text = "TextStyle") {} }
        item { HomeScreenItem(text = "TextStyle") {} }
    }
}

@Composable
fun HomeScreenItem(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
        )
    }
}