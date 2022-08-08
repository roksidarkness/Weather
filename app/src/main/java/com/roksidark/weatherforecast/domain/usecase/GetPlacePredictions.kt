package com.roksidark.weatherforecast.domain.usecase

import com.roksidark.weatherforecast.data.model.location.PlaceItem
import com.roksidark.weatherforecast.domain.repository.RemotePlaceRepository
import com.roksidark.weatherforecast.utils.Result

class GetPlacePredictions(
    private val repository: RemotePlaceRepository
) {
    suspend fun getPlacePredictions(query: String): Result<List<PlaceItem>> {
        return repository.getPlacePredictions(query)
    }
}