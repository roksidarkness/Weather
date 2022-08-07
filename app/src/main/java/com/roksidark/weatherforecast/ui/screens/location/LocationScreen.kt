package com.roksidark.weatherforecast.ui.screens.location

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.roksidark.weatherforecast.R
import com.roksidark.weatherforecast.feature_forecast.data.db.entity.Location
import com.roksidark.weatherforecast.feature_forecast.data.model.location.AddressItem
import com.roksidark.weatherforecast.feature_forecast.data.model.location.PlaceItem
import com.roksidark.weatherforecast.navigation.NavigationTree
import com.roksidark.weatherforecast.ui.screens.location.component.AutoCompleteEditText
import com.roksidark.weatherforecast.ui.screens.location.model.LocationAction
import com.roksidark.weatherforecast.ui.screens.textResource
import com.roksidark.weatherforecast.ui.theme.AppTheme
import com.roksidark.weatherforecast.utils.Constant.TAG
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LocationScreen(
    viewModel: LocationViewModel,
    navController: NavController
) {
    val scope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    val address by viewModel.address.collectAsState()
    val placesPredictions by viewModel.placePredictions.collectAsState()
    val showProgressbar by viewModel.showProgressBar.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(8.dp)) {
            AddressEdit(
                addressItem = address,
                modifier = Modifier,
                viewModel,
                navController,
                placesPredictions
            ) { action ->
                when (action) {
                    is LocationAction.OnLocationSelected -> {
                        keyboardController?.hide()
                        scope.launch {

                            viewModel.setLocationPrediction(action.selectedPlaceItem)
                        }
                    }

                    is LocationAction.OnLocationChange -> {
                        scope.launch {
                            viewModel.getPlacePredictions(action.address)
                        }
                    }

                    is LocationAction.OnLocationAutoCompleteDone -> {
                        keyboardController?.hide()
                    }

                    is LocationAction.OnLocationAutoCompleteClear -> {
                        viewModel.onLocationAutoCompleteClear()
                    }
                }
            }
        }

        if (showProgressbar) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddressEdit(
    addressItem: AddressItem,
    modifier: Modifier,
    viewModel: LocationViewModel,
    navController: NavController,
    addressPlaceItemPredictions: List<PlaceItem>,
    locationAction: (LocationAction) -> Unit
) {
    Column(
        modifier = modifier.padding(top = 2.dp, bottom = 2.dp),
        verticalArrangement = Arrangement.Center
    ) {
        AutoCompleteEditText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 0.dp,
                    bottom = 0.dp
                ),
            query = addressItem.streetAddress,
            useOutlined = true,
            queryLabel = textResource(id = R.string.label_location_search).toString(),
            onQueryChanged = { updatedAddress ->
                addressItem.streetAddress = updatedAddress
                locationAction(LocationAction.OnLocationChange(updatedAddress))
            },
            predictions = addressPlaceItemPredictions,
            onClearClick = {
                locationAction(LocationAction.OnLocationAutoCompleteClear)
            },
            onDoneActionClick = {
                locationAction(LocationAction.OnLocationAutoCompleteDone)
            },
            onItemClick = { placeItem ->
                locationAction(
                    LocationAction.OnLocationSelected(
                        placeItem
                    )
                )
            }
        ) {
            Text(it.address, fontSize = 14.sp)
        }

        Box {
            // val isLoading by viewModel.isLoading.observeAsState(initial = true)
            val addressItems by viewModel.addressItems.observeAsState(initial = emptyList())

            AddressList(addressItems = addressItems, viewModel = viewModel) { it ->
                navController.navigate("${NavigationTree.Weather.name}/${it}") {
                    popUpTo(NavigationTree.Weather.name)
                }
            }
            //  if (isLoading) {
            //      LoadingBar()
            //  }
        }
    }

}


@Composable
fun AddressList(
    addressItems: List<Location>,
    viewModel: LocationViewModel,
    onItemClicked: (id: String) -> Unit = { }
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(addressItems) { item ->
            AddressItemRow(item = item, viewModel = viewModel, onItemClicked = onItemClicked)
        }
    }
}

@Composable
fun AddressItemRow(
    item: Location,
    viewModel: LocationViewModel,
    onItemClicked: (id: String) -> Unit = { }
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .clickable {
                viewModel.getLocation(item.id)
                onItemClicked(item.id)
            }
    ) {
        Row(modifier = Modifier.animateContentSize()) {
            Box() {
            }
            RepoItemDetails(
                item = item,
                modifier = Modifier
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 20.dp,
                        bottom = 20.dp
                    )
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun RepoItemDetails(
    item: Location,
    modifier: Modifier
) {
    Log.d(TAG, item.toString())
    Column(modifier = modifier) {
        Text(
            text = item.address,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Left,
            color = AppTheme.colors.headerTextColor,
            style = MaterialTheme.typography.subtitle1,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}

