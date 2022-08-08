package com.roksidark.weatherforecast.data.db.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "location")
data class Location(
    @PrimaryKey
    val id: String,
    var address: String,
    val latitude: String,
    val longitude: String
) : Parcelable