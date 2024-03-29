package com.roksidark.weatherforecast.domain.usecase

import com.roksidark.weatherforecast.data.model.weather.WeatherForecastItem
import com.roksidark.weatherforecast.domain.repository.RemoteRepository


class GetWeatherRemotely(
    private val repository: RemoteRepository
) {
    suspend fun invoke(key: String, lat: String, lon: String, days: Int): WeatherForecastItem {
        return repository.getWeatherForecast(key, lat, lon, days)
    }
}