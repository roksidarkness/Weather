package com.roksidark.weatherforecast.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.roksidark.weatherforecast.data.db.entity.Location
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {

    @Query("SELECT * FROM location")
    fun getAll(): Flow<List<Location>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(locationEntities: Location)

    @Query("DELETE FROM location")
    suspend fun deleteAll()

    @Query("select * from location where id = :id")
    suspend fun getLocationById(id: String): Location
}