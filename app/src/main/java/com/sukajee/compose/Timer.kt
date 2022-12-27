package com.sukajee.compose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Timer(
    totalTimeInMillis: Long,
    modifier: Modifier = Modifier,
    handleColor: Color = Color(0xFF00C900),
    inactiveBarColor: Color = Color.Gray,
    activeBarColor: Color = Color.Green,
    initialValue: Float = 1f,
    strokeWidth: Dp = 15.dp
) {


    /**
     *
     * This timer doesn't tick on 1 seconds. if we compare with other timer,
     * this timer seems to be running slow.
     *
     * Not sure what should be fixed to get it worked correctly.
     *
     * */



    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

    var value by remember {
        mutableStateOf(initialValue)
    }

    var currentTime by remember {
        mutableStateOf(totalTimeInMillis)
    }

    var isTimerRunning by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = currentTime, key2 = isTimerRunning) {
        if(currentTime > 0 && isTimerRunning) {
            delay(100L)
            currentTime -= 100L
            value = currentTime / totalTimeInMillis.toFloat()
        }
    }

    Box(
        modifier = modifier
            .padding(32.dp)
            .onSizeChanged { size = it }
            .aspectRatio(1f),
        contentAlignment = Alignment.Center
    ) {

        Canvas(
            modifier = modifier.fillMaxSize()
        ) {
            drawArc(
                color = inactiveBarColor,
                startAngle = -215f,
                sweepAngle = 250f,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(width = strokeWidth.toPx())
            )
            drawArc(
                color = activeBarColor,
                startAngle = -215f,
                sweepAngle = 250f * value,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(width = strokeWidth.toPx())
            )

            val center = Offset(x = size.width / 2f, y = size.height / 2f)
            val angle = (250f * value + 145f) * (PI / 180f).toFloat()
            val radius = size.width / 2f

            val markerPosition = Offset(
                x = radius * cos(angle),
                y = radius * sin(angle)
            )

            drawPoints(
                listOf(Offset(center.x + markerPosition.x, center.y + markerPosition.y)),
                pointMode = PointMode.Points,
                color = handleColor,
                strokeWidth = (strokeWidth * 1.8f).toPx(),
                cap = StrokeCap.Round
            )
        }
        Text(
            text = (currentTime / 1000L).toString(),
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Magenta,
        )

        Button(
            modifier = Modifier.align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if(isTimerRunning || currentTime <= 0L) Color.Gray else Color.Green,
                contentColor = Color.White
            ),
            onClick = {
                if(currentTime <= 0L) {
                    currentTime = totalTimeInMillis
                    isTimerRunning = true
                } else isTimerRunning = !isTimerRunning
            }
        ) {
            Text(text = if(isTimerRunning && currentTime > 0L) "Stop" else if(!isTimerRunning && currentTime >=0) "Start" else "Restart")
        }
    }
}

@Preview
@Composable
fun ComposePreview() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Timer(100L * 1000L)
    }
}