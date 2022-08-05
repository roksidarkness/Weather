package com.roksidark.weatherforecast.feature_forecast.domain.usecase

import com.roksidark.weatherforecast.feature_forecast.data.model.weather.WeatherForecastItem
import com.roksidark.weatherforecast.feature_forecast.domain.repository.RemoteRepository


class GetWeatherRemotely(
    private val repository: RemoteRepository
) {
    suspend fun invoke(key: String, city: String): WeatherForecastItem {
        return repository.getWeatherForecast(key, city)
    }
}