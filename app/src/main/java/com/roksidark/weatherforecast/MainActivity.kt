package com.roksidark.weatherforecast

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.libraries.places.api.Places
import com.roksidark.weatherforecast.ui.screens.ApplicationScreen
import com.roksidark.weatherforecast.ui.theme.AppTheme
import com.roksidark.weatherforecast.ui.theme.WeatherForecastApplicationTheme
import com.roksidark.weatherforecast.utils.Constant.BUNDLE_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initPlaces()

        setContent {
            WeatherForecastApplicationTheme {
                Scaffold(
                    backgroundColor = AppTheme.colors.backgroundColor
                ) {
                    val systemUiController = rememberSystemUiController()
                    val primaryBackground = AppTheme.colors.secondaryBackgroundColor
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

    private fun initPlaces(){

        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, BuildConfig.PLACE_API_KEY)
        }
    }
}