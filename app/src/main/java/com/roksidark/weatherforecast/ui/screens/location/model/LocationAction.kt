package com.roksidark.weatherforecast.ui.screens.location.model

import com.roksidark.weatherforecast.feature_forecast.data.model.location.PlaceItem

sealed class LocationAction {
    object OnLocationAutoCompleteDone : LocationAction()
    object OnLocationAutoCompleteClear : LocationAction()
    data class OnLocationChange(val address: String) : LocationAction()
    data class OnLocationSelected(val selectedPlaceItem: PlaceItem) : LocationAction()
}