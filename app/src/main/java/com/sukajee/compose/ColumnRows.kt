package com.sukajee.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColumnRowsCompose() {
    Column(
        modifier = Modifier
            .background(Color.Green)
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
            .width(300.dp)
            .padding(25.dp)
            .border(15.dp, Color.Magenta)
            .padding(25.dp)
            .border(15.dp, Color.Red)
            .padding(25.dp)
            .border(border = BorderStroke(15.dp, Color.Blue), shape = CircleShape),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Hello")
        Text(text = "Good")
        Text(text = "Morning")
    }
}
