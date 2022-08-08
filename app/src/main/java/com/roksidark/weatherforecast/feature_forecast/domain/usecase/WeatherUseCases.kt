package com.roksidark.weatherforecast.feature_forecast.domain.usecase

data class WeatherUseCases(
    val getWeatherForecastRemotely: GetWeatherRemotely,
    val saveLocationLocal: SaveLocationLocal,
    val getLocationsLocal: GetLocationsLocal,
    val getLocationLocal: GetLocationLocal,
    val getLocationFromPlace: GetLocationFromPlace,
    val getPlacePredictions: GetPlacePredictions
)