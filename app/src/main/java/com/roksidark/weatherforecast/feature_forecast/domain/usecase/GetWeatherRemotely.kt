package com.roksidark.weatherforecast.feature_forecast.domain.usecase

import com.roksidark.weatherforecast.feature_forecast.data.model.weather.WeatherForecastItem
import com.roksidark.weatherforecast.feature_forecast.domain.repository.RemoteRepository


class GetWeatherRemotely(
    private val repository: RemoteRepository
) {
    suspend fun invoke(key: String, lat: String, lon: String): WeatherForecastItem {
        return repository.getWeatherForecast(key, lat, lon)
    }
}