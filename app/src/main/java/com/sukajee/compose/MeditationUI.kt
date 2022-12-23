package com.sukajee.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sukajee.compose.ui.theme.*
import kotlin.math.abs

@Composable
fun MeditationUI() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundBlack)
    ) {
        Column {
            GreetingSection()
            ChipSection(chips = listOf("Sweet sleep", "Insomnia", "Depression"))
            DailyThoughtSection()
            Spacer(modifier = Modifier.height(20.dp))
            FeatureSection(features = features)
        }
        BottomMenu(items = menuItems, modifier = Modifier.align(Alignment.BottomCenter))
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
            color = Color.White,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(16.dp)
        )
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 8.dp, end = 8.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(features.size) {
                FeatureItem(feature = features[it])
            }
        }
    }
}

@Composable
fun FeatureItem(
    feature: Feature
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(8.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        // medium path points
        val mediumPathPoint1 = Offset(0f, height * 0.29f)
        val mediumPathPoint2 = Offset(width * 0.2f, height * 0.35f)
        val mediumPathPoint3 = Offset(width * 0.45f, height * 0.1f)
        val mediumPathPoint4 = Offset(width * 0.75f, height * 0.75f)
        val mediumPathPoint5 = Offset(width * 1.2f, -height * 0.1f)

        val mediumPath = Path().apply {
            moveTo(mediumPathPoint1.x, mediumPathPoint1.y)
            standardQuadFromTo(mediumPathPoint1, mediumPathPoint2)
            standardQuadFromTo(mediumPathPoint2, mediumPathPoint3)
            standardQuadFromTo(mediumPathPoint3, mediumPathPoint4)
            standardQuadFromTo(mediumPathPoint4, mediumPathPoint5)
            lineTo(width * 1.2f, height * 1.2f)
            lineTo(-width * 1.2f, height * 1.2f)
            close()
        }

        // light path points
        val lightPathPoint1 = Offset(0f, height * 0.48f)
        val lightPathPoint2 = Offset(width * 0.15f, height * 0.55f)
        val lightPathPoint3 = Offset(width * 0.35f, height * 0.35f)
        val lightPathPoint4 = Offset(width * 0.65f, height * 0.95f)
        val lightPathPoint5 = Offset(width * 1.5f, -height * 0.1f)

        val lightPath = Path().apply {
            moveTo(lightPathPoint1.x, lightPathPoint1.y)
            standardQuadFromTo(lightPathPoint1, lightPathPoint2)
            standardQuadFromTo(lightPathPoint2, lightPathPoint3)
            standardQuadFromTo(lightPathPoint3, lightPathPoint4)
            standardQuadFromTo(lightPathPoint4, lightPathPoint5)
            lineTo(width * 1.2f, height * 1.2f)
            lineTo(-width * 1.2f, height * 1.2f)
            close()
        }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            drawPath(
                path = mediumPath,
                color = feature.mediumColor
            )

            drawPath(
                path = lightPath,
                color = feature.lightColor
            )
        }

        Box(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxSize()
        ) {
            Text(
                text = feature.title,
                color = TextWhite,
                modifier = Modifier.align(Alignment.TopStart),
                style = MaterialTheme.typography.h5,
                lineHeight = 24.sp
            )
            Icon(
                painter = painterResource(id = feature.iconId),
                contentDescription = feature.title,
                tint = Color.Magenta,
                modifier = Modifier.align(Alignment.BottomStart)
            )
            Text(
                text = "Start",
                color = Color.Yellow,
                modifier = Modifier
                    .clickable { }
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Blue)
                    .align(Alignment.BottomEnd)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }
    }
}

@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = Color(0xff14f7f4),
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = Color.LightGray,
    initialSelectedItemIndex: Int = 0
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(BackgroundBlack)
            .padding(10.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ) {
                selectedItemIndex = index
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = Color.Green,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = Color.Cyan,
    onItemClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemClick()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHighlightColor else Color.Transparent)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = item.icon),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = item.title,
            color = if(isSelected) activeTextColor else inactiveTextColor
        )
    }
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

fun Path.standardQuadFromTo(from: Offset, to: Offset) {
    return quadraticBezierTo(
        from.x,
        from.y,
        abs(from.x + to.x) / 2,
        abs(from.y + to.y) / 2
    )
}

val features = listOf(
    Feature(
        title = "Sleep meditation",
        iconId = R.drawable.ic_baseline_brightness_7_24,
        lightColor = Color(0xFF7B967B),
        mediumColor = Color(0xFF689468),
        darkColor = Color(0xFF368F36),
    ),
    Feature(
        title = "Tips for sleeping",
        iconId = R.drawable.ic_baseline_campaign_24,
        lightColor = Color(0xFF7B8396),
        mediumColor = Color(0xFF686F94),
        darkColor = Color(0xFF364F8F),
    ),
    Feature(
        title = "Night island",
        iconId = R.drawable.ic_baseline_directions_bike_24,
        lightColor = Color(0xFF96927B),
        mediumColor = Color(0xFF948968),
        darkColor = Color(0xFF8F7C36),
    ),
    Feature(
        title = "Calming sounds",
        iconId = R.drawable.ic_baseline_explore_24,
        lightColor = Color(0xFF8E7B96),
        mediumColor = Color(0xFF7A6894),
        darkColor = Color(0xFF65368F),
    ),
    Feature(
        title = "Depression therapy",
        iconId = R.drawable.ic_baseline_ondemand_video_24,
        lightColor = Color(0xFF957B96),
        mediumColor = Color(0xFF946890),
        darkColor = Color(0xFF8F3686),
    ),
    Feature(
        title = "All in one music",
        iconId = R.drawable.ic_baseline_radio_24,
        lightColor = Color(0xFF967B80),
        mediumColor = Color(0xFF946868),
        darkColor = Color(0xFF8F3636),
    )
)

val menuItems = listOf(
    BottomMenuContent(
        title = "Home",
        icon = R.drawable.ic_baseline_radio_24
    ),
    BottomMenuContent(
        title = "Meditate",
        icon = R.drawable.ic_baseline_ondemand_video_24
    ),
    BottomMenuContent(
        title = "Sleep",
        icon = R.drawable.ic_baseline_campaign_24
    ),
    BottomMenuContent(
        title = "Music",
        icon = R.drawable.ic_baseline_explore_24
    ),
    BottomMenuContent(
        title = "Profile",
        icon = R.drawable.ic_baseline_directions_bike_24
    )
)

data class BottomMenuContent(
    val title: String,
    @DrawableRes val icon: Int
)