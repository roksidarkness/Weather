package com.roksidark.weatherforecast.feature_forecast.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "location")
data class Location(
    @PrimaryKey
    val id: String,
    val address: String,
    val latitude: String,
    val longitude: String
)