package com.roksidark.weatherforecast.ui.screens.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roksidark.weatherforecast.BuildConfig
import com.roksidark.weatherforecast.R
import com.roksidark.weatherforecast.feature_forecast.data.db.entity.Location
import com.roksidark.weatherforecast.feature_forecast.data.model.location.AddressItem
import com.roksidark.weatherforecast.feature_forecast.data.model.location.PlaceItem
import com.roksidark.weatherforecast.feature_forecast.data.model.weather.DataItem
import com.roksidark.weatherforecast.feature_forecast.domain.usecase.WeatherUseCases
import com.roksidark.weatherforecast.utils.Constant.PARAMETER_DAYS
import com.roksidark.weatherforecast.utils.Constant.TAG
import com.roksidark.weatherforecast.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val useCases: WeatherUseCases
) : ViewModel() {

    private var _address: MutableStateFlow<AddressItem> = MutableStateFlow(AddressItem())
    private var _placePredictions: MutableStateFlow<List<PlaceItem>> =
        MutableStateFlow(arrayListOf())

    private var _showProgressBar: MutableStateFlow<Boolean> = MutableStateFlow(false)

    var address = _address.asStateFlow()
    var placePredictions = _placePredictions.asStateFlow()
    var showProgressBar = _showProgressBar.asStateFlow()


    private var _location = MutableLiveData<Location>()
    val location: LiveData<Location> = _location

    private var _addressItems = MutableLiveData<List<Location>>()
    val addressItems: LiveData<List<Location>> = _addressItems

    private var _weatherForecastItems = MutableLiveData<List<DataItem>>()
    val weatherForecastItems: LiveData<List<DataItem>> = _weatherForecastItems

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _weatherForecastItem = MutableLiveData<DataItem>()
    val weatherForecastItem: LiveData<DataItem> = _weatherForecastItem

    init{
        viewModelScope.launch {
            useCases.getLocationsLocal.invoke().collect {
                _addressItems.value = it
            }
        }
    }

    fun getPlacePredictions(query: String) {
        _address.value.streetAddress = query
        viewModelScope.launch {

            when (val placePredictionsResult = useCases.getPlacePredictions.getPlacePredictions(query)) {
                is Result.Success -> {
                    val placePredictions = placePredictionsResult.data
                    _placePredictions.value = placePredictions
                }
                is Result.Error -> {
                    Log.e(
                        TAG,
                        "An error occurred when retrieving the predictions for $query",
                        placePredictionsResult.exception
                    )
                }
                else -> {
                    Log.d(TAG, "Unexpected result")
                }
            }
        }
    }


    suspend fun setLocationPrediction(placeItem: PlaceItem) {

        viewModelScope.launch {

            when (val addressResult = useCases.getLocationFromPlace.getLocationFromPlace(placeItem.id!!)) {

                is Result.Success -> {
                    val addressFromPlace = addressResult.data
                    if (addressFromPlace != null) {
                        _address.value = addressFromPlace

                        saveLocationDb()
                    }
                    clearPredictions()
                }

                is Result.Error -> {
                    Log.e(
                        TAG,
                        "An error occurred when retrieving the address from Place  ${placeItem.id}",
                        addressResult.exception
                    )
                }
                else -> {
                    Log.d(TAG, "Unexpected result")
                }
            }
        }
    }

    fun onLocationAutoCompleteClear() {
        viewModelScope.launch {
            _address.value = AddressItem()
            clearPredictions()
        }
    }

    private fun clearPredictions() {
        _placePredictions.value = mutableListOf()
    }

    private suspend fun saveLocationDb (){
        val location = Location(_address.value.id,
            _address.value.address,
            _address.value.latitude.toString(),
            _address.value.longitude.toString())
        useCases.saveLocationLocal(location)
    }

    fun getLocation(id: String){
        viewModelScope.launch {
            _location.value = useCases.getLocationLocal(id)
            Log.d(TAG, _location.value.toString())
            getWeatherForecastList()
        }
    }

    private fun getWeatherForecastList() {

            _location.value?.let {
                viewModelScope.launch {
                    try {
                        val data = useCases.getWeatherForecastRemotely.invoke(
                            BuildConfig.API_KEY, it.latitude, it.longitude, PARAMETER_DAYS)
                        _weatherForecastItems.value = data.data
                        _isLoading.value = false
                    } catch (error: Exception) {
                        error.localizedMessage?.let { Log.d(TAG, it) }
                        _isLoading.value = false
                        _location.value?.address =
                            context.getString(R.string.label_location_check_network)
                    }
                }
            }
    }

    fun getWeatherDetails(date: String) {
        _weatherForecastItems.value?.let { it ->
            it.forEach { item ->
                if (item.valid_date == date){
                    _weatherForecastItem .postValue(item)
                }
            }
        }
    }
}