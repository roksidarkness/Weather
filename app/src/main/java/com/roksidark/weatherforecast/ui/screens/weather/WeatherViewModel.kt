package com.roksidark.weatherforecast.ui.screens.weather

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roksidark.weatherforecast.feature_forecast.domain.usecase.WeatherUseCases
import com.roksidark.weatherforecast.utils.Constant.API_KEY
import com.roksidark.weatherforecast.utils.Constant.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherUseCases: WeatherUseCases): ViewModel() {

    init {
        getWeatherForecast()
    }

    private fun getWeatherForecast() {
        try {
            viewModelScope.launch {
                   val data = weatherUseCases.getWeatherForecastRemotely.invoke(
                       API_KEY, "Warszawa,PL")
                Log.d(TAG, data.toString())
            }
        } catch (error: Exception) {
            error.localizedMessage?.let { Log.d(TAG, it) }
        }
    }
}