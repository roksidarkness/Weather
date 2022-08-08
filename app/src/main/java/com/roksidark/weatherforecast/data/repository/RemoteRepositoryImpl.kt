package com.roksidark.weatherforecast.data.repository

import com.roksidark.weatherforecast.data.model.weather.WeatherForecastItem
import com.roksidark.weatherforecast.data.rest.WeatherApi
import com.roksidark.weatherforecast.domain.repository.RemoteRepository

class RemoteRepositoryImpl(private val weatherApi: WeatherApi):
    RemoteRepository {
    override suspend fun getWeatherForecast(key: String,
                                            lat: String,
                                            lon: String,
                                            days: Int): WeatherForecastItem =
         weatherApi.getWeatherForecast(key, lat, lon, days)
}
