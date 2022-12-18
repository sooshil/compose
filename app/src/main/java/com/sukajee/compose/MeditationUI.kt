package com.sukajee.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyGridScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sukajee.compose.ui.theme.*

@Composable
fun MeditationUI() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundBlack)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            GreetingSection()
            ChipSection(chips = listOf("Sweet sleep", "Insomnia", "Depression"))
            DailyThoughtSection()
            Spacer(modifier = Modifier.height(20.dp))

        }
    }
}

@Composable
fun GreetingSection(
    name: String = "Sushil"
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Good morning, $name",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = TextWhite,
                modifier = Modifier.padding(vertical = 3.dp)
            )
            Text(
                text = "We wish you have a great day!",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = TextGray,
                modifier = Modifier.padding(vertical = 3.dp)
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search",
            tint = TextWhite,
            modifier = Modifier.size(36.dp)
        )
    }
}

@Composable
fun ChipSection(
    chips: List<String>
) {
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }
    LazyRow {
        items(count = chips.size) {
            Box(modifier = Modifier
                .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                .clickable {
                    selectedChipIndex = it
                }
                .clip(RoundedCornerShape(15.dp))
                .background(if (it == selectedChipIndex) ChipSelected else ChipNotSelected)
                .padding(15.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = chips[it], color = TextWhite, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun DailyThoughtSection(
    color: Color = LightRed
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(color)
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Daily Thought",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(vertical = 3.dp)
            )
            Text(
                text = "Meditation * 3-10 min",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = TextWhite,
                modifier = Modifier.padding(vertical = 3.dp)
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_play_circle),
            contentDescription = "Play",
            tint = Purple500,
            modifier = Modifier.size(42.dp) //.border(width = 3.dp, color = Color.Yellow, shape = CircleShape)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeatureSection(
    features: List<Feature>
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Featured",
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(16.dp)
        )
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 8.dp, end = 8.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(features.size) {

            }
        }
    }
}

@Composable
fun FeatureItem(
    feature: Feature
) {

}


@Preview
@Composable
fun ComposablePreview() {
    MeditationUI()
}

data class Feature(
    val title: String,
    @DrawableRes val iconId: Int,
    val lightColor: Color,
    val mediumColor: Color,
    val darkColor: Color
)