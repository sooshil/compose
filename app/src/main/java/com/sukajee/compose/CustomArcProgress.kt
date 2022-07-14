package com.sukajee.compose

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun CustomArcProgress(
    modifier: Modifier = Modifier,
    canvasSize: Dp = 300.dp,
    indicatorValue: Int = 0,
    maximumIndicatorValue: Int = 100,
    indicatorColor: Color = Color.Green,
    trackColor: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f),
    trackWidth: Float = 100f,
    insideTextDesc: String = "GB"
) {

    var allowedIndicatorValue by remember {
        mutableStateOf(maximumIndicatorValue)
    }
    allowedIndicatorValue =
        if (indicatorValue <= maximumIndicatorValue) indicatorValue else maximumIndicatorValue

    var animatedIndicatorValue by remember {
        mutableStateOf(0f)
    }

    LaunchedEffect(key1 = allowedIndicatorValue) {
        animatedIndicatorValue = allowedIndicatorValue.toFloat()
    }

    val percentage = (animatedIndicatorValue / maximumIndicatorValue) * 100

    val sweepAngle by animateFloatAsState(
        targetValue = (2.4 * percentage).toFloat(),
        animationSpec = tween(1000)
    )

    val receivedValue by animateIntAsState(
        targetValue = allowedIndicatorValue,
        animationSpec = tween(1000)
    )

    val animatedTextColor by animateColorAsState(
        targetValue = if (allowedIndicatorValue == 0) MaterialTheme.colors.onSurface.copy(
            alpha = 0.3f
        ) else Color.Red,
        animationSpec = tween(1000)
    )

    Column(
        modifier = modifier
            .size(canvasSize)
            .padding(8.dp)
            .drawBehind {
                val trackSize = size / 1.25f
                drawIndicatorBackgroundArc(
                    trackColor = trackColor,
                    trackSize = trackSize,
                    trackWidth = trackWidth
                )
                drawIndicatorArc(
                    indicatorColor = indicatorColor,
                    trackSize = trackSize,
                    trackWidth = trackWidth,
                    sweepAngle = sweepAngle
                )
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "$receivedValue ${insideTextDesc.uppercase().take(2)}",
            style = TextStyle(
                color = animatedTextColor,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

fun DrawScope.drawIndicatorBackgroundArc(
    trackColor: Color,
    trackSize: Size,
    trackWidth: Float
) {
    drawArc(
        size = trackSize,
        color = trackColor,
        startAngle = 150F,
        sweepAngle = 240F,
        useCenter = false,
        style = Stroke(
            width = trackWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - trackSize.width) / 2,
            y = (size.height - trackSize.width) / 2
        )
    )
}

fun DrawScope.drawIndicatorArc(
    indicatorColor: Color,
    trackSize: Size,
    trackWidth: Float,
    sweepAngle: Float
) {
    drawArc(
        size = trackSize,
        color = indicatorColor,
        startAngle = 150F,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = Stroke(
            width = trackWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - trackSize.width) / 2,
            y = (size.height - trackSize.width) / 2
        )
    )
}


@Preview(showBackground = true)
@Composable
fun IndicatorPreview() {
    CustomArcProgress()
}