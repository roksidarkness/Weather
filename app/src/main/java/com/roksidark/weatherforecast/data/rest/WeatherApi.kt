package com.roksidark.weatherforecast.data.rest

import com.roksidark.weatherforecast.data.model.weather.WeatherForecastItem
import retrofit2.http.*

interface WeatherApi {

    @GET("/v2.0/forecast/daily/")
    suspend fun getWeatherForecast(
        @Query("key") key: String,
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("days") days: Int
    ): WeatherForecastItem
}