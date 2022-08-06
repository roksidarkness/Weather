package com.roksidark.weatherforecast.feature_forecast.domain.usecase

import com.roksidark.weatherforecast.feature_forecast.data.db.entity.Location
import com.roksidark.weatherforecast.feature_forecast.domain.repository.LocalRepository

class SaveLocationLocal(
    private val repository: LocalRepository
) {
    suspend operator fun invoke(location: Location) {
        return repository.saveLocation(location)
    }
}