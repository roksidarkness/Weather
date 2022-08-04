package com.roksidark.weatherforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.roksidark.weatherforecast.ui.screens.ApplicationScreen
import com.roksidark.weatherforecast.ui.screens.weather.WeatherViewModel
import com.roksidark.weatherforecast.ui.theme.AppTheme
import com.roksidark.weatherforecast.ui.theme.WeatherForecastApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherForecastApplicationTheme {
                Scaffold(
                    backgroundColor = Color.Transparent
                ) {
                    val systemUiController = rememberSystemUiController()

                    // Set status bar color
                    val primaryBackground = AppTheme.colors.primaryBackground
                    SideEffect {
                        systemUiController.setSystemBarsColor(
                            color = primaryBackground,
                            darkIcons = true
                        )
                    }
                    ApplicationScreen()
                }
            }
        }
    }
}