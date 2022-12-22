package com.sukajee.compose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun AnalogClock() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {

            Canvas(
                modifier = Modifier.fillMaxSize(),
                onDraw = {
                    val radius = size.width * 0.4f

                    val eachMinuteAngle = 360 / 60f
                    (1..60).forEach {
                        val tickLength = if(it % 5 == 0) 16.dp.toPx() else 12.dp.toPx()
                        val tickColor = if(it % 5 == 0) Color.Yellow else Color.Gray
                        val stroke = if(it % 5 == 0) 3.dp.toPx() else 2.dp.toPx()
                        drawLine(
                            color = tickColor,
                            start = Offset(
                                x = (radius - tickLength) * cos(((it * eachMinuteAngle) - 90f) * (PI / 180f)).toFloat() + size.center.x,
                                y = (radius - tickLength) * sin(((it * eachMinuteAngle) - 90f) * (PI / 180f)).toFloat() + size.center.y
                            ),
                            end = Offset(
                                x = radius * cos(((it * eachMinuteAngle) - 90f) * (PI / 180f)).toFloat() + size.center.x,
                                y = radius * sin(((it * eachMinuteAngle) - 90f) * (PI / 180f)).toFloat() + size.center.y
                            ),
                            strokeWidth = stroke
                        )
                    }

                    drawCircle(
                        color = Color.Yellow,
                        style = Stroke(width = 10f),
                        radius = radius,
                        center = size.center
                    )

                    /* As of now, drawing text is not available in compose. So using legacy android view canvas */
                    drawContext.canvas.nativeCanvas.apply {

                    }
                }
            )
        }
    }
}


@Preview
@Composable
fun MyPreview() {
    AnalogClock()
}