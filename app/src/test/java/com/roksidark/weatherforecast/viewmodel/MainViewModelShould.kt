package com.roksidark.weatherforecast.viewmodel

import android.content.Context
import com.nhaarman.mockitokotlin2.mock
import com.roksidark.weatherforecast.data.model.weather.DataItem
import com.roksidark.weatherforecast.domain.repository.LocalRepository
import com.roksidark.weatherforecast.domain.repository.RemotePlaceRepository
import com.roksidark.weatherforecast.domain.repository.RemoteRepository
import com.roksidark.weatherforecast.domain.usecase.*
import com.roksidark.weatherforecast.ui.screens.viewmodel.MainViewModel
import com.roksidark.weatherforecast.utils.BaseUnitTest
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class MainViewModelShould: BaseUnitTest() {

    private val locationLocal: LocalRepository = mock()
    private val weatherRemote: RemoteRepository = mock()
    private val weatherPlaceRepository: RemotePlaceRepository = mock()

    private val getWeatherRemotely = GetWeatherRemotely(
        weatherRemote
    )
    private val saveLocationLocal = SaveLocationLocal(
        locationLocal
    )
    private val getLocationsLocal = GetLocationsLocal(
        locationLocal
    )
    private val getLocationLocal = GetLocationLocal(
        locationLocal
    )
    private val getLocationFromPlace = GetLocationFromPlace(
        weatherPlaceRepository
    )
    private val getPlacePredictions = GetPlacePredictions(
        weatherPlaceRepository
    )

    private val useCases = WeatherUseCases(
        getPlacePredictions = getPlacePredictions,
        getLocationFromPlace = getLocationFromPlace,
        getLocationLocal = getLocationLocal,
        getLocationsLocal = getLocationsLocal,
        saveLocationLocal = saveLocationLocal,
        getWeatherForecastRemotely = getWeatherRemotely
    )

    private val contextMock: Context = mock()

    @Test
    fun test_mainViewModel_shouldExist() = runTest {

        var viewModel = MainViewModel(
            contextMock,
            useCases
        )

        assert(viewModel != null)
    }

    @Test
    fun test_mainViewModel_shouldRunAnReturnLocationNull() {
        var viewModel = MainViewModel(
            contextMock,
            useCases
        )
        val locations = viewModel.location.value

        assertEquals(locations, null)
    }

    @Test
    fun test_mainViewModel_shouldRunAnReturnDataItemsNull() {
        var viewModel = MainViewModel(
            contextMock,
            useCases
        )
        val locations = viewModel.addressItems.value

        assertEquals(locations, null)
    }


    @Test
    fun test_mainViewModel_shouldRunAnReturnWeatherForecastItemNull() {
        var viewModel = MainViewModel(
            contextMock,
            useCases
        )
        val locations = viewModel.weatherForecastItem.value

        assertEquals(locations, null)
    }

    @Test
    fun test_mainViewModel_shouldRunAnGetDataItemId() {
        var item: DataItem = mock()
        var viewModel = MainViewModel(
            contextMock,
            useCases
        )

        val weatherForecastItem = viewModel.weatherForecastItem.value
        assertEquals(weatherForecastItem, item.datetime)
    }

    @Test
    fun test_mainViewModel_shouldRunAnGetEmptyDataItems() {
        var viewModel = MainViewModel(
            contextMock,
            useCases
        )

        val items = viewModel.weatherForecastItems.value
        assertEquals(items?.count(), 0)
    }
}