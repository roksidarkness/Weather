package com.roksidark.weatherforecast.feature_forecast.domain.usecase

import com.roksidark.weatherforecast.feature_forecast.data.model.location.AddressItem
import com.roksidark.weatherforecast.feature_forecast.data.model.location.PlaceItem
import com.roksidark.weatherforecast.feature_forecast.domain.repository.RemotePlaceRepository
import com.roksidark.weatherforecast.utils.Result

class GetLocationFromPlace (
    private val repository: RemotePlaceRepository
) {
    suspend fun getLocationFromPlace(placeId: String): Result<AddressItem?>{
        return repository.getLocationFromPlace(placeId)
    }
}