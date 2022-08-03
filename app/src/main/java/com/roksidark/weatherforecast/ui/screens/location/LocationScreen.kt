package com.roksidark.weatherforecast.ui.screens.location

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun LocationScreen(
    locationViewModel: LocationViewModel,
    navController: NavController
) {
    Text(text = "Location")
}