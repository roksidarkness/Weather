package com.roksidark.weatherforecast.feature_forecast.domain.usecase

import com.roksidark.weatherforecast.feature_forecast.data.db.entity.Location
import com.roksidark.weatherforecast.feature_forecast.domain.repository.LocalRepository

class GetLocationLocal(
    private val repository: LocalRepository
) {
    suspend operator fun invoke(id: String): Location {
        return repository.getLocationById(id)
    }
}