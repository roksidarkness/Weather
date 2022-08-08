package com.roksidark.weatherforecast.domain.repository

import com.roksidark.weatherforecast.data.db.entity.Location
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
        suspend fun getLocations(): Flow<List<Location>>
        suspend fun getLocationById(id: String): Location
        suspend fun saveLocation(location: Location)
}