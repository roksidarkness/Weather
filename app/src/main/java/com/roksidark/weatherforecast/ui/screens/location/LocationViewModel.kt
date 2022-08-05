package com.roksidark.weatherforecast.ui.screens.location

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roksidark.weatherforecast.feature_forecast.data.model.location.AddressItem
import com.roksidark.weatherforecast.feature_forecast.data.model.location.PlaceItem
import com.roksidark.weatherforecast.feature_forecast.data.repository.RemotePlaceRepositoryImpl
import com.roksidark.weatherforecast.utils.Constant.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.roksidark.weatherforecast.utils.Result

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val remotePlaceRepository: RemotePlaceRepositoryImpl
) : ViewModel() {

    private var _address: MutableStateFlow<AddressItem> = MutableStateFlow(AddressItem())
    private var _placePredictions: MutableStateFlow<List<PlaceItem>> =
        MutableStateFlow(arrayListOf())

    private var _showProgressBar: MutableStateFlow<Boolean> = MutableStateFlow(false)

    var address = _address.asStateFlow()
    var placePredictions = _placePredictions.asStateFlow()
    var showProgressBar = _showProgressBar.asStateFlow()


    private var _list: MutableList<AddressItem>  = mutableListOf()
    private var _addressItems = MutableLiveData<List<AddressItem>>()
    val addressItems: LiveData<List<AddressItem>> = _addressItems

    fun getPlacePredictions(query: String) {
        _address.value.streetAddress = query
        viewModelScope.launch {

            when (val placePredictionsResult = remotePlaceRepository.getPlacePredictions(query)) {
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
                else -> {//does not apply here
                }
            }
        }
    }


    suspend fun setLocationPrediction(placeItem: PlaceItem) {

        viewModelScope.launch {

            when (val addressResult = remotePlaceRepository.getLocationFromPlace(placeItem.id!!)) {

                is Result.Success -> {
                    val addressFromPlace = addressResult.data
                    if (addressFromPlace != null) {
                        _address.value = addressFromPlace
                        _list.add(_address.value)
                        _addressItems.postValue(_list)
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
}