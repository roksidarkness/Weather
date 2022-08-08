package com.roksidark.weatherforecast.data.repository

import com.roksidark.weatherforecast.data.db.WeatherDatabase
import com.roksidark.weatherforecast.data.db.entity.Location
import com.roksidark.weatherforecast.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val db: WeatherDatabase): LocalRepository{

    override suspend fun getLocations(): Flow<List<Location>> {
        return db.locationDao().getAll()
    }

    override suspend fun getLocationById(id: String): Location {
        return db.locationDao().getLocationById(id)
    }

    override suspend fun saveLocation(location: Location) {
        db.locationDao().insert(location)
    }
}