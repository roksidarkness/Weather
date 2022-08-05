package com.roksidark.weatherforecast.ui.screens

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.roksidark.weatherforecast.navigation.NavigationTree
import com.roksidark.weatherforecast.ui.screens.details.DetailsScreen
import com.roksidark.weatherforecast.ui.screens.location.LocationScreen
import com.roksidark.weatherforecast.ui.screens.location.LocationViewModel
import com.roksidark.weatherforecast.ui.screens.splash.SplashScreen
import com.roksidark.weatherforecast.ui.screens.weather.WeatherScreen
import com.roksidark.weatherforecast.ui.screens.weather.WeatherViewModel

@Composable
fun ApplicationScreen() {

    val navController = rememberNavController()
    val viewModelLocation = hiltViewModel<LocationViewModel>()
    val viewModelWeather = hiltViewModel<WeatherViewModel>()

    NavHost(navController = navController, startDestination = NavigationTree.Splash.name) {

        composable(NavigationTree.Splash.name) { SplashScreen(navController) }
        composable(NavigationTree.Location.name) {
            LocationScreen(viewModel = viewModelLocation, navController = navController)
        }
        composable("${NavigationTree.Weather.name}/{location}") { backStackEntry ->
            WeatherScreen(backStackEntry.arguments?.getString("location").orEmpty(),
                viewModel = viewModelWeather, navController)
        }
        composable("${NavigationTree.Details.name}/{selected_weather}") { backStackEntry ->
            DetailsScreen(backStackEntry.arguments?.getString("selected_weather").orEmpty())
        }
    }
}

@Composable
@ReadOnlyComposable
fun textResource(@StringRes id: Int): CharSequence =
    LocalContext.current.resources.getText(id)