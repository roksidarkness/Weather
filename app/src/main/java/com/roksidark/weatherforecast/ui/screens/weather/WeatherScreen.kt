package com.roksidark.weatherforecast.ui.screens.weather

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.roksidark.weatherforecast.feature_forecast.data.db.entity.Location
import com.roksidark.weatherforecast.ui.screens.location.LocationViewModel

@Composable
fun WeatherScreen(
    id: String,
    viewModel: LocationViewModel,
    navController: NavController
) {

    val location by viewModel.location.observeAsState()

    location?.let {
        Text(it.latitude +" "+ it.longitude)
    }
}