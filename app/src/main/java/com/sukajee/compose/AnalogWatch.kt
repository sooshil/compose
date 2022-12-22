package com.sukajee.compose

import android.graphics.Paint
import android.graphics.Rect
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import java.time.LocalTime
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun AnalogClock(
    isClockRunning: Boolean = true,
    time: LocalTime = LocalTime.now()
) {
    var hour by remember { mutableStateOf(time.hour) }
    var minute by remember { mutableStateOf(time.minute) }
    var second by remember { mutableStateOf(time.second) }

    var hourAngle by remember {
        mutableStateOf(0.0)
    }
    var minuteAngle by remember {
        mutableStateOf(0.0)
    }

    LaunchedEffect(key1 = minute) {
        hourAngle = (hour * 30) + (minute / 60.0 * 30.0) - 90
    }

    LaunchedEffect(key1 = second) {
        minuteAngle = (minute * 6) + (second / 60.0 * 6) - 90
    }

    LaunchedEffect(key1 = isClockRunning) {
        while (isClockRunning) {
            second++
            if(second > 60) {
                second = 1
                minute++
            }
            if(minute > 60) {
                minute = 1
                hour++
            }
            delay(1000)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
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
                        val tickLength = if (it % 5 == 0) 16.dp.toPx() else 12.dp.toPx()
                        val tickColor = if (it % 5 == 0) Color.Yellow else Color.Gray
                        val stroke = if (it % 5 == 0) 3.dp.toPx() else 2.dp.toPx()
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

                        /** Drawing text */
                        /* As of now, drawing text is not available in compose. So using legacy android view canvas */
                        if (it % 5 == 0) {
                            drawContext.canvas.nativeCanvas.apply {
                                val text = (it / 5).toString()
                                val xPosition =
                                    (radius - tickLength * 1.8f) * cos(((it * eachMinuteAngle) - 90f) * (PI / 180f)).toFloat() + size.center.x
                                val yPosition =
                                    (radius - tickLength * 1.8f) * sin(((it * eachMinuteAngle) - 90f) * (PI / 180f)).toFloat() + size.center.y
                                val paint = Paint()
                                paint.textSize = radius * 0.15f
                                paint.color = android.graphics.Color.YELLOW
                                val textRect = Rect()
                                paint.getTextBounds(text, 0, text.length, textRect)
                                drawText(
                                    text,
                                    xPosition - (textRect.width()) / 2,
                                    yPosition + (textRect.height()) / 2,
                                    paint
                                )
                            }
                        }
                    }

                    drawCircle(
                        color = Color.Yellow,
                        style = Stroke(width = 10f),
                        radius = radius,
                        center = size.center
                    )

                    // the pivot point for the hands.
                    drawCircle(
                        color = Color.Yellow,
                        style = Fill,
                        radius = radius * 0.05f,
                        center = size.center
                    )

                    // hour hand
                    val hourHandLength = radius * 0.55f
                    drawLine(
                        color = Color.Yellow,
                        start = Offset(
                            x = (size.center.x - hourHandLength * 0.20 * cos(hourAngle * PI / 180)).toFloat(),
                            y = (size.center.y - hourHandLength * 0.20 * sin(hourAngle * PI / 180)).toFloat()
                        ),
                        end = Offset(
                            x = (size.center.x + hourHandLength * cos(hourAngle * PI / 180)).toFloat(),
                            y = (size.center.y + hourHandLength * sin(hourAngle * PI / 180)).toFloat()
                        ),
                        strokeWidth = 6.dp.toPx(),
                        cap = StrokeCap.Butt
                    )

                    // minute hand
                    val minuteHandLength = radius * 0.80f
                    drawLine(
                        color = Color.Yellow,
                        start = Offset(
                            x = (size.center.x - minuteHandLength * 0.15 * cos(minuteAngle * PI / 180)).toFloat(),
                            y = (size.center.y - minuteHandLength * 0.15 * sin(minuteAngle * PI / 180)).toFloat()
                        ),
                        end = Offset(
                            x = (size.center.x + minuteHandLength * cos(minuteAngle * PI / 180)).toFloat(),
                            y = (size.center.y + minuteHandLength * sin(minuteAngle * PI / 180)).toFloat()
                        ),
                        strokeWidth = 6.dp.toPx(),
                        cap = StrokeCap.Butt
                    )

                    // second hand
                    val secondHandLength = radius * 0.85f
                    val secondAngle = ((360 * second / 60) - 90)
                    drawLine(
                        color = Color.Magenta,
                        start = Offset(
                            x = (size.center.x - secondHandLength * 0.2 * cos(secondAngle * PI / 180)).toFloat(),
                            y = (size.center.y - secondHandLength * 0.2 * sin(secondAngle * PI / 180)).toFloat()
                        ),
                        end = Offset(
                            x = (size.center.x + secondHandLength * cos(secondAngle * PI / 180)).toFloat(),
                            y = (size.center.y + secondHandLength * sin(secondAngle * PI / 180)).toFloat()
                        ),
                        strokeWidth = 2.dp.toPx(),
                        cap = StrokeCap.Butt
                    )

                    // redrawing center circle with seconds' hand color
                    drawCircle(
                        color = Color.Magenta,
                        style = Fill,
                        radius = radius * 0.05f,
                        center = size.center
                    )
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