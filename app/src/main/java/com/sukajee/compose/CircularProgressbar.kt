package com.sukajee.compose

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


@Composable
fun CircularProgressBar(
    currentValue: Float,
    fullValue: Float,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 28.sp,
    radius: Dp = 50.dp,
    color: Color = Color.Green,
    strokeWidth: Dp = 8.dp,
    animationDuration: Int = 1000,
    animationDelay: Int = 0
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }

    val currentPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) currentValue / fullValue else 0f,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = animationDelay
        )
    )

    LaunchedEffect(key1 = true) {
        //delay(500)
        animationPlayed = true
    }

    Box(
        modifier = modifier.size(radius * 2f),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = modifier.size(radius * 2f)) {
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360 * currentPercentage.value,
                useCenter = false, /* if used, it will draw radius from the starting and ending of the arc */
                style = Stroke(
                    width = strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )                
            )
        }
        Text(
            text = (currentPercentage.value * 100).toInt().toString(),
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = fontSize
        )
    }
}