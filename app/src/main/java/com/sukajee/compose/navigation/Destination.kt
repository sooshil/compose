package com.sukajee.compose.navigation

sealed class Destination(val route: String) {
    object Home : Destination(route = "home")
    object AnalogClock : Destination(route = "analog_clock")
    object ArcProgress : Destination(route = "arc_progress")
    object Borders : Destination(route = "borders")
    object CircularProgressbar : Destination(route = "circular_progress")
    object ColumnRows : Destination(route = "column_rows")
    object ConstraintLayout : Destination(route = "constraint_layout")
    object CustomArcProgress : Destination(route = "custom_arc_progress")
    object InstagramProfile : Destination(route = "instagram_profile")
    object Lists : Destination(route = "lists")
    object MediationUI : Destination(route = "meditation_ui")
    object SimpleAnimation : Destination(route = "simple_animation")
    object SimpleUI : Destination(route = "simple_ui")
    object State : Destination(route = "state")
    object TextFieldSnackbar : Destination(route = "text_field_snackbar")
    object TextStyle : Destination(route = "text_style")
    object Timer : Destination(route = "timer")
}
