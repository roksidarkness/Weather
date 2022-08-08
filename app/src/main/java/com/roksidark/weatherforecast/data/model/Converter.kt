package com.roksidark.weatherforecast.data.model

import com.roksidark.weatherforecast.data.db.entity.Location
import com.roksidark.weatherforecast.data.model.location.AddressItem

fun AddressItem.toLocalLocation() = Location(
    id = id,
    address = name,
    longitude = longitude.toString(),
    latitude = latitude.toString()
)