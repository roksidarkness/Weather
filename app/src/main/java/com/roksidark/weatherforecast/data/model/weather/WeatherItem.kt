package com.roksidark.weatherforecast.data.model.weather

import com.google.gson.annotations.SerializedName

data class WeatherItem (
	@SerializedName("icon") val icon : String,
	@SerializedName("code") val code : Int,
	@SerializedName("description") val description : String
)