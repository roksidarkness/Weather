package com.roksidark.weatherforecast.feature_forecast.domain.repository

import com.roksidark.weatherforecast.feature_forecast.data.model.location.AddressItem
import com.roksidark.weatherforecast.feature_forecast.data.model.location.PlaceItem
import com.roksidark.weatherforecast.utils.Result

interface RemotePlaceRepository {

    suspend fun getPlacePredictions(query: String): Result<List<PlaceItem>>

    suspend fun getLocationFromPlace(placeId: String): Result<AddressItem?>

}