package com.roksidark.weatherforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.libraries.places.api.Places
import com.roksidark.weatherforecast.ui.screens.ApplicationScreen
import com.roksidark.weatherforecast.ui.screens.textResource
import com.roksidark.weatherforecast.ui.theme.AppTheme
import com.roksidark.weatherforecast.ui.theme.WeatherForecastApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initPlaces()

        setContent {
            WeatherForecastApplicationTheme {
                Scaffold(
                    backgroundColor = AppTheme.colors.backgroundColor,
                    topBar = {
                        MainAppBar()
                    }
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

@Composable
private fun MainAppBar() {
    TopAppBar(
        title = { Text(textResource(id = R.string.app_title_bat).toString(),
            color = AppTheme.colors.headerTextColor) },
        backgroundColor = AppTheme.colors.secondaryBackgroundColor
    )
}
