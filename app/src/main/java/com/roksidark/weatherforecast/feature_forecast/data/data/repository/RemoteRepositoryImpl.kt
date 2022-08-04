package com.roksidark.weatherforecast.feature_forecast.data.data.repository


import WeatherForecast
import com.roksidark.weatherforecast.feature_forecast.data.rest.WeatherApi
import com.roksidark.weatherforecast.feature_forecast.domain.repository.RemoteRepository

class RemoteRepositoryImpl(private val weatherApi: WeatherApi):
    RemoteRepository {
    override suspend fun getWeatherForecast(key: String, city: String): WeatherForecast  =
         weatherApi.getWeatherForecast(key, city)
}
