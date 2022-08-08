package com.roksidark.weatherforecast.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.roksidark.weatherforecast.data.db.dao.LocationDao
import com.roksidark.weatherforecast.data.db.entity.Location

@Database(entities = [Location::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun locationDao(): LocationDao
}