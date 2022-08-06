package com.roksidark.weatherforecast.feature_forecast.data.repository

import com.roksidark.weatherforecast.feature_forecast.data.db.WeatherDatabase
import com.roksidark.weatherforecast.feature_forecast.data.db.entity.Location
import com.roksidark.weatherforecast.feature_forecast.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val db: WeatherDatabase): LocalRepository{

    override suspend fun getLocations(): Flow<List<Location>> {
        return db.locationDao().getAll()
    }

    override suspend fun saveLocation(location: Location) {
        db.locationDao().insert(location)
    }
}