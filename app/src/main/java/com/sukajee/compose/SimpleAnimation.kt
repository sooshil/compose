package com.sukajee.compose

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
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
        animationSpec = tween( /** can change animation type as we want. */
            durationMillis = 1000,
            delayMillis = 100,
            easing = FastOutLinearInEasing
        ),
//        animationSpec = keyframes {
//            durationMillis = 5000
//            sizeState at 0 with LinearEasing
//            sizeState * 1.5f at 1000 with FastOutLinearInEasing
//            sizeState * 2f at 5000
//        }
    )

    /** For infinite animation */
    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 5000),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .size(size)
            .background(/* Color.GREEN */ color)
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