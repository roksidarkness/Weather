package com.roksidark.weatherforecast.data.model.location

import java.util.*

data class AddressItem(
    var id: String = "",
    var name: String = "",
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    var address: String = "",
    var streetAddress: String = "",
    var city: String = "",
    var state: String = "",
    var country: String = "",
    var postalCode: String = "",
    var knownName: String = "",
    var description: String = "",
    var createdOn: Date = Date(),
    var isFavorite: Boolean = false
) {

    fun toCoordinates(): String {
        return "$latitude,$longitude"
    }
}