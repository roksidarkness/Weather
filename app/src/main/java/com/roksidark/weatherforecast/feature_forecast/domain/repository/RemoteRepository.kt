package com.roksidark.weatherforecast.feature_forecast.domain.repository

import com.roksidark.weatherforecast.feature_forecast.data.model.weather.WeatherForecastItem

interface RemoteRepository {

    suspend fun getWeatherForecast(
        key: String,
        city: String
    ): WeatherForecastItem
}