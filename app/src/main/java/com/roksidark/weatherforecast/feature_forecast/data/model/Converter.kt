package com.roksidark.weatherforecast.feature_forecast.data.model

import com.roksidark.weatherforecast.feature_forecast.data.db.entity.Location
import com.roksidark.weatherforecast.feature_forecast.data.model.location.AddressItem

fun AddressItem.toLocalLocation() = Location(
    id = id,
    address = name,
    longitude = longitude.toString(),
    latitude = latitude.toString()
)