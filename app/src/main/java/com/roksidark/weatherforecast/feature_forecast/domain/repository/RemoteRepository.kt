package com.roksidark.weatherforecast.feature_forecast.domain.repository

import WeatherForecast

interface RemoteRepository {

    suspend fun getWeatherForecast(
        key: String,
        city: String
    ): WeatherForecast
}