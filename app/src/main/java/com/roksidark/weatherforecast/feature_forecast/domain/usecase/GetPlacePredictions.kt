package com.roksidark.weatherforecast.feature_forecast.domain.usecase

import com.roksidark.weatherforecast.feature_forecast.data.model.location.PlaceItem
import com.roksidark.weatherforecast.feature_forecast.domain.repository.RemotePlaceRepository
import com.roksidark.weatherforecast.utils.Result

class GetPlacePredictions(
    private val repository: RemotePlaceRepository
) {
    suspend fun getPlacePredictions(query: String): Result<List<PlaceItem>> {
        return repository.getPlacePredictions(query)
    }
}