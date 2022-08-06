package com.roksidark.weatherforecast.ui.screens.weather

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.roksidark.weatherforecast.R
import com.roksidark.weatherforecast.feature_forecast.data.db.entity.Location
import com.roksidark.weatherforecast.navigation.NavigationTree
import com.roksidark.weatherforecast.ui.screens.location.AddressEdit
import com.roksidark.weatherforecast.ui.screens.location.AddressList
import com.roksidark.weatherforecast.ui.screens.location.LocationViewModel
import com.roksidark.weatherforecast.ui.screens.location.model.LocationAction
import com.roksidark.weatherforecast.ui.screens.textResource
import com.roksidark.weatherforecast.ui.theme.AppTheme
import kotlinx.coroutines.launch

@Composable
fun WeatherScreen(
    id: String,
    viewModel: LocationViewModel,
    navController: NavController
) {

    val location by viewModel.location.observeAsState()

    location?.let {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = it.address,
                    color = AppTheme.colors.primaryTextColor,
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            end = 2.dp,
                            top = 2.dp,
                            bottom = 16.dp
                        ),
                    fontSize = 22.sp
                )

                Box {
                    // val isLoading by viewModel.isLoading.observeAsState(initial = true)
                  ///  val addressItems by viewModel.addressItems.observeAsState(initial = emptyList())

                  //  AddressList(addressItems = addressItems, viewModel = viewModel) { it ->
                  //      navController.navigate("${NavigationTree.Weather.name}/${it}") {
                  //          popUpTo(NavigationTree.Weather.name)
                 //       }
                 //   }
                    //  if (isLoading) {
                    //      LoadingBar()
                    //  }
                }

            }

          //  if (showProgressbar) {
          //      CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
          //  }
        }
    }


}