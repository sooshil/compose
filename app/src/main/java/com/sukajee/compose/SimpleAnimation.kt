package com.sukajee.compose

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SimpleAnimation() {
    var sizeState by remember { mutableStateOf(200.dp) }
    val size by animateDpAsState(
        targetValue = sizeState,
        animationSpec = tween(
            durationMillis = 1000,
            delayMillis = 100
        )
    )
    Box(
        modifier = Modifier
            .size(size)
            .background(Color.Green)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                sizeState += 50.dp
            }) {
                Text(
                    text = "Click Me ++",
                    fontWeight = FontWeight.Bold
                )
            }
            Button(onClick = {
                sizeState -= 50.dp
            }) {
                Text(
                    text = "Click Me --",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}