package com.roksidark.weatherforecast.ui.screens.weather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roksidark.weatherforecast.feature_forecast.data.db.entity.Location
import com.roksidark.weatherforecast.feature_forecast.domain.usecase.WeatherUseCases
import com.roksidark.weatherforecast.utils.Constant.API_KEY
import com.roksidark.weatherforecast.utils.Constant.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherUseCases: WeatherUseCases): ViewModel() {

    private var _location = MutableLiveData<Location>()
    val location: LiveData<Location> = _location

    init {
       // getWeatherForecast()
        Log.d(TAG,"")
    }

    private fun getWeatherForecast() {
        try {
            viewModelScope.launch {
                   val data = weatherUseCases.getWeatherForecastRemotely.invoke(
                       API_KEY, "Warszawa,PL")
                Log.d(TAG, data.toString())
            }
        } catch (error: Exception) {
            error.localizedMessage?.let { Log.d(TAG, it) }
        }
    }

     fun getLocation(id: String){
         viewModelScope.launch {
             _location.value = weatherUseCases.getLocationLocal(id)
             Log.d(TAG, _location.value.toString())
         }
    }
}