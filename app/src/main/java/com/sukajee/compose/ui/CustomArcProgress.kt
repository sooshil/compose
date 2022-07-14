package com.sukajee.compose.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
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

@Composable
fun CustomArcProgress(
    modifier: Modifier = Modifier,
    canvasSize: Dp = 300.dp,
    indicatorColor: Color = Color.Green,
    trackColor: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f),
    trackWidth: Float = 100f
) {
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
                    sweepAngle = 50f
                )
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

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