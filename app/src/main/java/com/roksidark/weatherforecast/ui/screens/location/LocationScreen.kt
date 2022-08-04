package com.roksidark.weatherforecast.ui.screens.location

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.roksidark.weatherforecast.ui.screens.weather.WeatherViewModel

@Composable
fun LocationScreen(
    viewModel: WeatherViewModel,
    navController: NavController
) {
    Text(text = "Location")
}