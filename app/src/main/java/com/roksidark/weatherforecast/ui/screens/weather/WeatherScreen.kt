package com.roksidark.weatherforecast.ui.screens.weather

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.roksidark.weatherforecast.feature_forecast.data.model.location.AddressItem

@Composable
fun WeatherScreen(
    location: String,
    viewModel: WeatherViewModel,
    navController: NavController
) {
    Text(location)
}