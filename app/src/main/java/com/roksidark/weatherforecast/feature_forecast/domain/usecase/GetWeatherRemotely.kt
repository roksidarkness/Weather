package com.roksidark.weatherforecast.feature_forecast.domain.usecase

import WeatherForecast
import com.roksidark.weatherforecast.feature_forecast.domain.repository.RemoteRepository


class GetWeatherRemotely(
    private val repository: RemoteRepository
) {
    suspend fun invoke(key: String, city: String): WeatherForecast {
        return repository.getWeatherForecast(key, city)
    }
}