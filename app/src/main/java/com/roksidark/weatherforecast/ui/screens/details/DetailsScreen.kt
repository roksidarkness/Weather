package com.roksidark.weatherforecast.ui.screens.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roksidark.weatherforecast.ui.screens.location.LocationViewModel
import com.roksidark.weatherforecast.ui.theme.AppTheme

@Composable
fun DetailsScreen(
    selectedWeather: String,
    viewModel: LocationViewModel
) {
    val weather by viewModel.weatherForecastItem.observeAsState()

    weather?.let{
        Box(modifier = Modifier.fillMaxSize()) {
            Card(
                shape = RoundedCornerShape(8.dp),
                elevation = 2.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = it.max_temp.toString(),
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
                    Text(
                        text = it.min_temp.toString(),
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

                    Text(
                        text = it.app_max_temp.toString(),
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

                    Text(
                        text = it.rh.toString(),
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

                    Text(
                        text = it.pres.toString(),
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

                    Text(
                        text = it.uv.toString(),
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
                }
            }
        }
    }

}