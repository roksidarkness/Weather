package com.roksidark.weatherforecast.domain.usecase

import com.roksidark.weatherforecast.data.db.entity.Location
import com.roksidark.weatherforecast.domain.repository.LocalRepository

class SaveLocationLocal(
    private val repository: LocalRepository
) {
    suspend operator fun invoke(location: Location) {
        return repository.saveLocation(location)
    }
}