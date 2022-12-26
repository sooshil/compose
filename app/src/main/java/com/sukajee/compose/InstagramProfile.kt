package com.sukajee.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sukajee.compose.ui.theme.BackgroundBlack

@Composable
fun InstagramProfile() {

    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBar(name = "sukajee's profile")
        ProfileSection()
        Spacer(modifier = Modifier.height(24.dp))
        ButtonSection(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(24.dp))
        HighlightSection(
            modifier = Modifier
                .padding(start = 8.dp)
                .fillMaxWidth()
                .height(80.dp)
                .padding(end = 8.dp),
            highlights = highlightList
        )
        Spacer(modifier = Modifier.height(24.dp))
        TabLayoutSection(
            tabs = highlightList,
            onTabSelected = {
                selectedTabIndex = it
            }
        )
        when (selectedTabIndex) {
            0 -> PostSection(
                posts = listOf(
                    painterResource(id = R.drawable.ic_baseline_directions_bike_24),
                    painterResource(id = R.drawable.ic_baseline_radio_24),
                    painterResource(id = R.drawable.ic_baseline_explore_24),
                    painterResource(id = R.drawable.ic_baseline_ondemand_video_24),
                    painterResource(id = R.drawable.ic_baseline_campaign_24),
                    painterResource(id = R.drawable.ic_baseline_directions_bike_24),
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun TopBar(
    name: String, modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            Modifier.size(28.dp),
            tint = Color.Black
        )
        Text(
            text = name,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_bell),
            contentDescription = "Bell",
            Modifier.size(28.dp),
            tint = Color.Black
        )
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "More",
            Modifier.size(28.dp),
            tint = Color.Black
        )
    }
}

@Composable
fun ProfileSection(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RoundedImage(
                image = painterResource(id = R.drawable.profile),
                modifier = modifier
                    .size(100.dp)
                    .weight(3f)
            )
            StatSection(
                modifier.weight(7f),
                postCount = "670",
                followersCount = "520K",
                followingCount = "235"
            )
        }
        BioSection(
            title = "Senior Android Engineer",
            text = "10+ years of experience in coding,\n" + "Several awards and achievements\n" + "Freelancer, Mentor and youtuber.",
            url = "https://facebook.com/sooshil",
            followers = listOf("Lionel Messi", "Joe Biden"),
            othersCount = 53
        )
    }
}

@Composable
fun BioSection(
    modifier: Modifier = Modifier,
    title: String,
    text: String,
    url: String?,
    followers: List<String>,
    othersCount: Int
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            letterSpacing = 1.2.sp,
            lineHeight = 24.sp
        )
        Text(
            text = text, fontSize = 18.sp, lineHeight = 24.sp
        )
        url?.let {
            Text(
                text = url, fontSize = 18.sp, color = Color(0xFF4141ff)
            )
        }
        if (followers.isEmpty().not()) {
            Text(
                text = buildAnnotatedString {
                    val boldStyle = SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                    append("Followed by ")
                    followers.forEachIndexed { index, name ->
                        pushStyle(boldStyle)
                        append(name)
                        pop()
                        if (index < followers.size - 1) {
                            append(", ")
                        }
                    }
                    append(" and ")
                    pushStyle(boldStyle)
                    append("$othersCount others.")
                }, fontSize = 16.sp
            )
        }

    }
}

@Composable
fun StatSection(
    modifier: Modifier = Modifier, postCount: String, followersCount: String, followingCount: String
) {
    Row(
        modifier = modifier.padding(horizontal = 3.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        StatItem(count = postCount, type = "Posts")
        StatItem(count = followersCount, type = "Followers")
        StatItem(count = followingCount, type = "Followings")
    }
}

@Composable
fun StatItem(
    count: String, type: String
) {
    Column(
        modifier = Modifier.padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = count, fontWeight = FontWeight.Bold, fontSize = 18.sp
        )
        Text(
            text = type, fontWeight = FontWeight.Normal, fontSize = 16.sp, color = Color.Gray
        )
    }
}

@Composable
fun ButtonSection(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ButtonItem(label = "Following", icon = Icons.Default.KeyboardArrowDown)
        ButtonItem(label = "Message")
        ButtonItem(label = "Email")
        ButtonItem(icon = Icons.Default.KeyboardArrowDown)
    }
}

@Composable
fun ButtonItem(
    modifier: Modifier = Modifier, label: String? = null, icon: ImageVector? = null
) {
    Row(
        modifier = modifier
            .height(28.dp)
            .clip(RoundedCornerShape(5.dp))
            .border(
                width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(5.dp)
            )
            .padding(vertical = 3.dp, horizontal = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        label?.let {
            Text(
                text = it, fontWeight = FontWeight.SemiBold, fontSize = 14.sp
            )
        }
        icon?.let {
            Icon(
                imageVector = icon, contentDescription = null
            )
        }
    }
}

@Composable
fun HighlightSection(
    modifier: Modifier = Modifier,
    highlights: List<Highlight>,
) {
    LazyRow {
        items(highlights.size) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(highlights[it].icon),
                    contentDescription = null,
                    modifier = modifier
                        .aspectRatio(1f, matchHeightConstraintsFirst = true)
                        .border(
                            width = 2.dp,
                            color = Color.Green,
                            shape = CircleShape
                        )
                        .clip(CircleShape)
                        .background(BackgroundBlack)
                        .padding(18.dp)
                )
                Text(
                    text = highlights[it].name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun RoundedImage(
    image: Painter, modifier: Modifier = Modifier
) {
    Image(
        painter = image,
        contentDescription = null,
        modifier = modifier
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .border(
                width = 3.dp, color = Color.LightGray, shape = CircleShape
            )
            .padding(4.dp)
            .clip(CircleShape)
    )
}

@Composable
fun TabLayoutSection(
    modifier: Modifier = Modifier,
    tabs: List<Highlight>, //used highlights because it also has image and text.
    onTabSelected: (selectedIndex: Int) -> Unit
) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    val inactiveTabColor = Color(0xff777777)
    TabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = Color.Transparent,
        contentColor = Color.Black,
        modifier = modifier
    ) {
        tabs.forEachIndexed { index, item ->
            Tab(
                selected = selectedTabIndex == index,
                selectedContentColor = Color.Black,
                unselectedContentColor = inactiveTabColor,
                onClick = {
                    selectedTabIndex = index
                    onTabSelected(index)
                }
            ) {
                Icon(
                    painter = painterResource(id = item.icon),
                    contentDescription = item.name,
                    tint = if (selectedTabIndex == index) Color.Black else inactiveTabColor,
                    modifier = modifier
                        .padding(10.dp)
                        .size(20.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostSection(
    modifier: Modifier = Modifier,
    posts: List<Painter>
) {
    LazyVerticalGrid(
        horizontalArrangement = Arrangement.spacedBy(1.dp), //these two is used to display the borders
        verticalArrangement = Arrangement.spacedBy(1.dp),   //between the post photos in lazy grid.
        cells = GridCells.Fixed(3)
    ) {
        items(posts.size) {
            Image(
                painter = posts[it],
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .aspectRatio(1f)
                    .background(Color.LightGray)
            )
        }
    }
}

data class Highlight(
    val name: String, val icon: Int
)

@Preview
@Composable
fun MyComposePreview() {
    InstagramProfile()
}

val highlightList = listOf(
    Highlight(
        name = "Youtube",
        icon = R.drawable.ic_baseline_radio_24
    ),
    Highlight(
        name = "Q&A",
        icon = R.drawable.ic_baseline_campaign_24

    ),
    Highlight(
        name = "Discord",
        icon = R.drawable.ic_baseline_ondemand_video_24

    ),
    Highlight(
        name = "Telegram",
        icon = R.drawable.ic_baseline_explore_24
    ),
    Highlight(
        name = "Facebook",
        icon = R.drawable.ic_baseline_directions_bike_24
    )
)