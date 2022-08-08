package com.roksidark.weatherforecast.domain.usecase

import com.roksidark.weatherforecast.data.db.entity.Location
import com.roksidark.weatherforecast.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

class GetLocationsLocal(
    private val repository: LocalRepository
) {
    suspend operator fun invoke(): Flow<List<Location>> {
        return repository.getLocations()
    }
}