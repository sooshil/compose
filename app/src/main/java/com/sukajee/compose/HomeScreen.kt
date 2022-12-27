package com.sukajee.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sukajee.compose.navigation.Destination

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        item {
            HomeScreenItem(text = "AnalogClock") {
                navController.navigate(route = Destination.AnalogClock.route)
            }
        }
        item { HomeScreenItem(text = "ArcProgress") { navController.navigate(route = Destination.ArcProgress.route) } }
        item { HomeScreenItem(text = "Borders") { navController.navigate(route = Destination.Borders.route) } }
        item {
            HomeScreenItem(text = "CircularProgressbar") {
                navController.navigate(
                    route = Destination.CircularProgressbar.route + "/" + 95f + "/" + 100f
                )
            }
        }
        item { HomeScreenItem(text = "ColumnRows") { navController.navigate(route = Destination.ColumnRows.route) } }
        item { HomeScreenItem(text = "ConstraintLayout") { navController.navigate(route = Destination.ConstraintLayout.route) } }
        item { HomeScreenItem(text = "CustomArcProgress") { navController.navigate(route = Destination.CustomArcProgress.route) } }
        item { HomeScreenItem(text = "InstagramProfile") { navController.navigate(route = Destination.InstagramProfile.route) } }
        item { HomeScreenItem(text = "Lists") { navController.navigate(route = Destination.Lists.route) } }
        item { HomeScreenItem(text = "MeditationUI") { navController.navigate(route = Destination.MediationUI.route) } }
        item { HomeScreenItem(text = "SimpleAnimation") { navController.navigate(route = Destination.SimpleAnimation.route) } }
        item { HomeScreenItem(text = "SimpleUI") { navController.navigate(route = Destination.SimpleUI.route) } }
        item { HomeScreenItem(text = "State") { navController.navigate(route = Destination.State.route) } }
        item { HomeScreenItem(text = "TextFieldSnackbar") { navController.navigate(route = Destination.TextFieldSnackbar.route) } }
        item { HomeScreenItem(text = "TextStyle") { navController.navigate(route = Destination.TextStyle.route) } }
        item {
            HomeScreenItem(text = "Timer") {
                navController.navigate(
                    route = Destination.Timer.route + "/" + 120000L
                )
            }
        }
    }
}

@Composable
fun HomeScreenItem(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            ),
            overflow = TextOverflow.Ellipsis
        )
    }
}