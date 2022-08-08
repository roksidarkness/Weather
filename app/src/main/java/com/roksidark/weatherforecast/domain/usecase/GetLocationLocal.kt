package com.roksidark.weatherforecast.domain.usecase

import com.roksidark.weatherforecast.data.db.entity.Location
import com.roksidark.weatherforecast.domain.repository.LocalRepository

class GetLocationLocal(
    private val repository: LocalRepository
) {
    suspend operator fun invoke(id: String): Location {
        return repository.getLocationById(id)
    }
}