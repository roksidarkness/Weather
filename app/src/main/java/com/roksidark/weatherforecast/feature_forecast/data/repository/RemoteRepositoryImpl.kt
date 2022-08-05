package com.roksidark.weatherforecast.feature_forecast.data.repository


import com.roksidark.weatherforecast.feature_forecast.data.model.weather.WeatherForecastItem
import com.roksidark.weatherforecast.feature_forecast.data.rest.WeatherApi
import com.roksidark.weatherforecast.feature_forecast.domain.repository.RemoteRepository

class RemoteRepositoryImpl(private val weatherApi: WeatherApi):
    RemoteRepository {
    override suspend fun getWeatherForecast(key: String, city: String): WeatherForecastItem =
         weatherApi.getWeatherForecast(key, city)
}
