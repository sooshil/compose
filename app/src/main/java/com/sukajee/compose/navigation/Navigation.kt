package com.sukajee.compose.navigation

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sukajee.compose.*

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destination.Home.route
    ) {
        composable(route = Destination.Home.route) { HomeScreen(navController) }
        composable(route = Destination.AnalogClock.route) { AnalogClock() }
        composable(route = Destination.ArcProgress.route) { ArcProgress() }
        composable(route = Destination.Borders.route) { BorderCompose() }
        composable(
            route = Destination.CircularProgressbar.route + "/{currentValue}/{fullValue}",
            arguments = listOf(
                navArgument(name = "currentValue") {
                    type = NavType.FloatType
                    // defaultValue = 55f
                    // nullable = false
                },
                navArgument(name = "fullValue") {
                    type = NavType.FloatType
                }
            )
        ) { entry ->
            CircularProgressBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .padding(16.dp),
                currentValue = entry.arguments?.getFloat("currentValue") ?: 75f,
                fullValue = entry.arguments?.getFloat("fullValue") ?: 100f,
            )
        }
        composable(route = Destination.ColumnRows.route) { ColumnRowsCompose() }
        composable(route = Destination.ConstraintLayout.route) { ConstraintLayoutComposable() }
        composable(route = Destination.CustomArcProgress.route) { CustomArcProgress() }
        composable(route = Destination.InstagramProfile.route) { InstagramProfile() }
        composable(route = Destination.Lists.route) { ListComposable() }
        composable(route = Destination.MediationUI.route) { MeditationUI() }
        composable(route = Destination.SimpleAnimation.route) { SimpleAnimation() }
        composable(route = Destination.SimpleUI.route) { SimpleUi() }
        composable(route = Destination.State.route) { State() }
        composable(route = Destination.TextFieldSnackbar.route) { TextFieldsSnackbar() }
        composable(route = Destination.TextStyle.route) { TextStyle() }
        composable(
            route = Destination.Timer.route + "/{totalTimeInMillis}",
            arguments = listOf(
                navArgument(name = "totalTimeInMillis") {
                    type = NavType.LongType
                }
            )
        ) { entry ->
            val totalTimeInMillis = entry.arguments?.getLong("totalTimeInMillis") ?: 100L
            Timer(totalTimeInMillis = totalTimeInMillis)
        }
    }
}