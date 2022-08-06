package com.roksidark.weatherforecast.ui.screens.weather

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
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.roksidark.weatherforecast.R
import com.roksidark.weatherforecast.feature_forecast.data.db.entity.Location
import com.roksidark.weatherforecast.feature_forecast.data.model.weather.DataItem
import com.roksidark.weatherforecast.feature_forecast.data.model.weather.WeatherForecastItem
import com.roksidark.weatherforecast.navigation.NavigationTree
import com.roksidark.weatherforecast.ui.screens.location.*
import com.roksidark.weatherforecast.ui.screens.location.model.LocationAction
import com.roksidark.weatherforecast.ui.screens.textResource
import com.roksidark.weatherforecast.ui.theme.AppTheme
import com.roksidark.weatherforecast.utils.Constant
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
                     val isLoading by viewModel.isLoading.observeAsState(initial = true)
                     val weatherForecastItems by viewModel.weatherForecastItems.observeAsState(initial = emptyList())

                    WeatherForecastList(items = weatherForecastItems, viewModel = viewModel) { it ->
                        navController.navigate("${NavigationTree.Details.name}/${it}") {
                            popUpTo(NavigationTree.Details.name)
                        }
                    }
                      if (isLoading) {
                          LoadingBar()
                      }
                }

            }

          //  if (showProgressbar) {
          //      CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
          //  }
        }
    }
}

@Composable
fun WeatherForecastList(
    items: List<DataItem>,
    viewModel: LocationViewModel,
    onItemClicked: (id: String) -> Unit = { }
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(items) { item ->
            WeatherForecastItemRow(item = item, viewModel = viewModel, onItemClicked = onItemClicked)
        }
    }
}

@Composable
fun WeatherForecastItemRow(
    item: DataItem,
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
                onItemClicked(item.datetime)
            }
    ) {
        Row(modifier = Modifier.animateContentSize()) {
            Box() {
            }
            WeatherForecastItemDetails(
                item = item,
                viewModel = viewModel,
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
fun WeatherForecastItemDetails(
    item: DataItem,
    viewModel: LocationViewModel,
    modifier: Modifier
) {
    Log.d(Constant.TAG, item.toString())
    Row(modifier = Modifier
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 20.dp,
                bottom = 20.dp
            )) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.datetime,
                modifier = Modifier,
                textAlign = TextAlign.Left,
                color = AppTheme.colors.headerTextColor,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }

        Text(
            text = item.temp.toString(),
            modifier = Modifier,
            textAlign = TextAlign.Right,
            color = AppTheme.colors.headerTextColor,
            style = MaterialTheme.typography.subtitle1,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun LoadingBar() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}