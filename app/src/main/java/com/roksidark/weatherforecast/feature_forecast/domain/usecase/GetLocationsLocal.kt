package com.roksidark.weatherforecast.feature_forecast.domain.usecase

import com.roksidark.weatherforecast.feature_forecast.data.db.entity.Location
import com.roksidark.weatherforecast.feature_forecast.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

class GetLocationsLocal(
    private val repository: LocalRepository
) {
    suspend operator fun invoke(): Flow<List<Location>> {
        return repository.getLocations()
    }
}