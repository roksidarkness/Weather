package com.roksidark.weatherforecast.data.repository

import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.roksidark.weatherforecast.data.model.location.AddressItem
import com.roksidark.weatherforecast.data.model.location.PlaceItem
import com.roksidark.weatherforecast.domain.repository.RemotePlaceRepository
import com.roksidark.weatherforecast.utils.Constant.ADMINISTRATIVE_AREA_LEVEL_1
import com.roksidark.weatherforecast.utils.Constant.COUNTRY
import com.roksidark.weatherforecast.utils.Constant.LOCALITY
import com.roksidark.weatherforecast.utils.Constant.POSTAL
import com.roksidark.weatherforecast.utils.Constant.ROUTE
import com.roksidark.weatherforecast.utils.Constant.STREET_NUMBER
import com.roksidark.weatherforecast.utils.Result
import com.roksidark.weatherforecast.utils.Result.Error
import com.roksidark.weatherforecast.utils.Result.Success
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class RemotePlaceRepositoryImpl @Inject constructor(
    private val placesClient: PlacesClient
): RemotePlaceRepository{
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun getPlacePredictions(query: String): Result<List<PlaceItem>> = withContext(
        ioDispatcher
    ) {

        try {
            val predictedPlaces: ArrayList<PlaceItem> = ArrayList()
            val token = AutocompleteSessionToken.newInstance()
            val predictionsRequest = FindAutocompletePredictionsRequest.builder().apply {
                setQuery(query)
                sessionToken = token
            }.build()
            val autocompletePredictionsTask =
                placesClient.findAutocompletePredictions(predictionsRequest).await()
            val findAutocompletePredictionsResponse =
                autocompletePredictionsTask.autocompletePredictions
            for (prediction in findAutocompletePredictionsResponse) {
                val place = PlaceItem(
                    prediction.placeId,
                    "",
                    "",
                    0.0,
                    0.0,
                    0f,
                    prediction.getFullText(null).toString()
                )
                predictedPlaces.add(place)
            }
            return@withContext Success(
                predictedPlaces
            )
        } catch (ex: Exception) {
            return@withContext Error(ex)
        }
    }

    override suspend fun getLocationFromPlace(placeId: String): Result<AddressItem?> =
        withContext(ioDispatcher) {
            try {
                val placeFields = arrayListOf(
                    Place.Field.ID,
                    Place.Field.LAT_LNG,
                    Place.Field.ADDRESS,
                    Place.Field.ADDRESS_COMPONENTS,
                    Place.Field.PHOTO_METADATAS
                )

                val request = FetchPlaceRequest.builder(placeId, placeFields).build()
                val fetchPlaceResponse = placesClient.fetchPlace(request).await()
                val place = fetchPlaceResponse?.place
                if (place == null)
                    return@withContext Success(place)
                val locationItem = getAddressItem(place)
                return@withContext Success(locationItem)
            } catch (ex: Exception) {
                return@withContext Error(ex)
            }
        }

    private fun getAddressItem(place: Place): AddressItem {

        val latLng: LatLng = place.latLng!!
        val addressComponents = place.addressComponents?.asList()
        val photoMetadata = place.photoMetadatas?.get(0)
        photoMetadata?.attributions
        var countryName = ""
        var postalCode = ""
        var city = ""
        var state = ""
        var streetNumber = ""
        var route = ""
        if (addressComponents != null && addressComponents.size > 0) {
            for (addressComponent in addressComponents) {
                if (addressComponent.types.contains(COUNTRY)) {
                    countryName = addressComponent.name
                } else if (addressComponent.types.contains(POSTAL)) {
                    postalCode = addressComponent.name
                } else if (addressComponent.types.contains(LOCALITY)) {
                    city = addressComponent.name
                } else if (addressComponent.types.contains(ADMINISTRATIVE_AREA_LEVEL_1)) {
                    state = addressComponent.name
                } else if (addressComponent.types.contains(STREET_NUMBER)) {
                    streetNumber = addressComponent.name
                } else if (addressComponent.types.contains(ROUTE)) {
                    route = addressComponent.name
                }
            }
        }

        return AddressItem(
            "${latLng.latitude},${latLng.longitude}",
            "",
            latLng.latitude,
            latLng.longitude,
            place.address!!,
            "$streetNumber $route",
            city,
            state,
            countryName,
            postalCode,
            "",
            "", Date(),
            false
        )
    }
}