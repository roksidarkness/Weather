package com.roksidark.weatherforecast.feature_forecast.data.rest

import WeatherForecast
import retrofit2.http.*

interface WeatherApi {

    @GET("/v2.0/forecast/daily/")
    suspend fun getWeatherForecast(
        @Query("key") key: String,
        @Query("city") clientSecret: String
    ): WeatherForecast
}