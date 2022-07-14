package com.sukajee.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextStyle() {
    val fontFamily = FontFamily(
        Font(R.font.rajdhani_bold, FontWeight.Bold),
        Font(R.font.rajdhani_regular, FontWeight.Normal),
        Font(R.font.rajdhani_light, FontWeight.Light),
        Font(R.font.rajdhani_semibold, FontWeight.SemiBold),
        Font(R.font.rajdhani_medium, FontWeight.Medium)
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF018d15))
            .padding(16.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(Color.Green, fontSize = 50.sp)
                ) {
                    append("J")
                }
                append("etPack ")
                withStyle(
                    style = SpanStyle(Color.Green, fontSize = 50.sp)
                ) {
                    append("C")
                }
                append("ompose")
            },
            /**
             * text = "Jetpack Compose",
             * */
            color = Color.White,
            fontFamily = fontFamily,
            fontSize = 40.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Italic,
            textDecoration = TextDecoration.Underline

        )
    }
}