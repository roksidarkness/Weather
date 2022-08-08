package com.roksidark.weatherforecast.domain.repository

import com.roksidark.weatherforecast.data.model.weather.WeatherForecastItem

interface RemoteRepository {

    suspend fun getWeatherForecast(
        key: String,
        lat: String,
        lon: String,
        days: Int
    ): WeatherForecastItem
}