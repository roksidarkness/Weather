package com.roksidark.weatherforecast.domain.usecase

import com.roksidark.weatherforecast.data.model.location.AddressItem
import com.roksidark.weatherforecast.domain.repository.RemotePlaceRepository
import com.roksidark.weatherforecast.utils.Result

class GetLocationFromPlace (
    private val repository: RemotePlaceRepository
) {
    suspend fun getLocationFromPlace(placeId: String): Result<AddressItem?>{
        return repository.getLocationFromPlace(placeId)
    }
}