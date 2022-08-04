package com.roksidark.weatherforecast.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.roksidark.weatherforecast.navigation.NavigationTree
import com.roksidark.weatherforecast.ui.screens.details.DetailsScreen
import com.roksidark.weatherforecast.ui.screens.location.LocationScreen
import com.roksidark.weatherforecast.ui.screens.splash.SplashScreen
import com.roksidark.weatherforecast.ui.screens.weather.WeatherScreen
import com.roksidark.weatherforecast.ui.screens.weather.WeatherViewModel

@Composable
fun ApplicationScreen() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationTree.Splash.name) {

        composable(NavigationTree.Splash.name) { SplashScreen(navController) }
        composable(NavigationTree.Location.name) {
            val viewModel = hiltViewModel<WeatherViewModel>()
            LocationScreen(viewModel = viewModel, navController = navController)
        }
        composable("${NavigationTree.Weather.name}/{location}") { backStackEntry ->
            WeatherScreen(backStackEntry.arguments?.getString("location").orEmpty(),
                navController)
        }
        composable("${NavigationTree.Details.name}/{selected_weather}") { backStackEntry ->
            DetailsScreen(backStackEntry.arguments?.getString("selected_weather").orEmpty())
        }
    }
}