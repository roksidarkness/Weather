package com.roksidark.weatherforecast.feature_forecast.domain.repository

import com.roksidark.weatherforecast.feature_forecast.data.db.entity.Location
import com.roksidark.weatherforecast.feature_forecast.data.model.location.AddressItem
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
        suspend fun getLocations(): Flow<List<Location>>
     //   suspend fun getLocation(id: String): Location
        suspend fun saveLocation(location: Location)
}