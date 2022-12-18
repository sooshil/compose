package com.sukajee.compose

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.Calendar
import kotlin.math.cos
import kotlin.math.sin

//@Composable
//fun AnalogClock(
//    showSeconds: Boolean = true
//) {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xE2C6D4FD)),
//        contentAlignment = Alignment.TopStart
//    ) {

//        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
//        val minute = Calendar.getInstance().get(Calendar.MINUTE)
//        val second = Calendar.getInstance().get(Calendar.SECOND)
//
//        // Calculate the angles for the hour, minute, and second hands
//        val hourAngle = (hour / 12f) * 360f - 90f
//        val minuteAngle = (minute / 60f) * 360f - 90f
//        val secondAngle = (second / 60f) * 360f - 90f
//
//
//
//        Canvas(
//            modifier = Modifier
//                .size(200.dp)
//                .border(width = 1.dp, color = Color.Magenta)
//        ) {
//            // Calculate the endpoints of the hour, minute, and second hands
//
//            val hourEndX = 100.dp.toPx() + 50 * cos(Math.toDegrees(hourAngle.toDouble())).dp.toPx()
//            val hourEndY = 100.dp.toPx() + 50 * sin(Math.toDegrees(hourAngle.toDouble())).dp.toPx()
//            val minuteEndX = 100.dp.toPx() + 75 * cos(Math.toDegrees(minuteAngle.toDouble())).dp.toPx()
//            val minuteEndY = 100.dp.toPx() + 75 * sin(Math.toDegrees(minuteAngle.toDouble())).dp.toPx()
//            val secondEndX = 100.dp.toPx() + 90 * cos(Math.toDegrees(secondAngle.toDouble())).dp.toPx()
//            val secondEndY = 100.dp.toPx() + 90 * sin(Math.toDegrees(secondAngle.toDouble())).dp.toPx()
//
//            drawCircle(
//                color = Color(0x3142F035)
//            )
//            drawPath(
//                Path().apply {
//                    drawLine(
//                        color = Color.Black,
//                        start = Offset(100.dp.toPx(), 100.dp.toPx()),
//                        end = Offset(hourEndX, hourEndY),
//                        strokeWidth = 15f
//                    )
//                },
//                color = Color.Black
//            )
//
//            drawPath(
//                Path().apply {
//                    drawLine(
//                        color = Color.Black,
//                        start = Offset(100.dp.toPx(), 100.dp.toPx()),
//                        end = Offset(minuteEndX, minuteEndY),
//                        strokeWidth = 15f
//                    )
//                },
//                color = Color.Black
//            )
//
//            drawPath(
//                Path().apply {
//                    drawLine(
//                        color = Color.Black,
//                        start = Offset(100.dp.toPx(), 100.dp.toPx()),
//                        end = Offset(secondEndX, secondEndY),
//                        strokeWidth = 6f
//                    )
//                },
//                color = Color.Black
//            )
//        }


@Composable
fun AnalogClock(
    showSeconds: Boolean = true,
    animationSpec: AnimationSpec<Float> = TweenSpec(durationMillis = 1000, easing = LinearEasing)
) {
    val elapsedTime = animatedFloat(0f)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xE2C6D4FD)),
        contentAlignment = Alignment.TopStart
    ) {
        ClockFace(showSeconds, elapsedTime.value)
        onCommit(elapsedTime.value) {
            elapsedTime.setValue(elapsedTime.value + 1, animationSpec)
        }
    }
}

@Composable
fun ClockFace(showSeconds: Boolean, elapsedTime: Float) {
    Canvas(
        modifier = Modifier
            .size(200.dp)
            .border(width = 1.dp, color = Color.Magenta)
    ) {
        // Calculate the endpoints of the hour, minute, and second hands
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val minute = Calendar.getInstance().get(Calendar.MINUTE)
        val second = Calendar.getInstance().get(Calendar.SECOND)

        // Calculate the angles for the hour, minute, and second hands
        val hourAngle = ((hour / 12f) * 360f - 90f).toDouble()
        val minuteAngle = ((minute / 60f) * 360f - 90f).toDouble()
        val secondAngle = ((second / 60f) * 360f - 90f).toDouble()

        val hourEndX = 100.dp.toPx() + 50 * cos(Math.toRadians(hourAngle)).dp.toPx()
        val hourEndY = 100.dp.toPx() + 50 * sin(hourAngle).dp.toPx()
        val minuteEndX = 100.dp.toPx() + 75 * cos(minuteAngle).dp.toPx()
        val minuteEndY = 100.dp.toPx() + 75 * sin(minuteAngle).dp.toPx()
        val secondEndX = 100.dp.toPx() + 90 * cos(secondAngle).dp.toPx()
        val secondEndY = 100.dp.toPx() + 90 * sin(secondAngle).dp.toPx()

        val path = Path().apply {
            addCircle(
                Offset(100.dp.toPx(), 100.dp.toPx()),
                100.dp.toPx(),
                Path.Direction.CW
            )
            moveTo(100.dp.toPx(), 100.dp.toPx())
            lineTo(hourEndX, hourEndY)
            moveTo(100.dp.toPx(), 100.dp.toPx())
            lineTo(minuteEndX, minuteEndY)
            if (showSeconds) {
                moveTo(100.dp.toPx(), 100.dp.toPx())
                lineTo(secondEndX, secondEndY)
            }
        }
        drawPath(path, color = Color.Black, style = Path.FillType.EvenOdd)
    }
}







@Preview
@Composable
fun MyPreview() {
    AnalogClock()
}