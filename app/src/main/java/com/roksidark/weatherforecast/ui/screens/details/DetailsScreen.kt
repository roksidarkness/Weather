package com.roksidark.weatherforecast.ui.screens.details

import androidx.compose.foundation.Image
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
import coil.compose.rememberImagePainter
import com.roksidark.weatherforecast.R
import com.roksidark.weatherforecast.ui.screens.location.LocationViewModel
import com.roksidark.weatherforecast.ui.screens.textResource
import com.roksidark.weatherforecast.ui.theme.AppTheme
import com.roksidark.weatherforecast.utils.Constant
import com.roksidark.weatherforecast.utils.formatTime


@Composable
fun DetailsScreen(
    selectedWeather: String,
    viewModel: LocationViewModel
) {
    val weather by viewModel.weatherForecastItem.observeAsState()
    val location by viewModel.locationCurrent.observeAsState()

    weather?.let{
            item ->
        Box(modifier = Modifier.fillMaxSize()) {
            Card(
                shape = RoundedCornerShape(8.dp),
                elevation = 2.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            ) {
                Column(modifier = Modifier.padding(8.dp)) {

                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(150.dp)
                            .padding(bottom = 30.dp),
                        painter = rememberImagePainter(Constant.IMAGE_URL + item.weather.icon+
                                Constant.IMAGE_FORMAT),
                        contentDescription = "Weather"
                    )
                    TextDetails(text = item.high_temp.toInt().toString(),
                        degree = textResource(id =
                        R.string.label_weather_details_degree_temperature).toString(),
                        textLabel = textResource(id =
                        R.string.label_weather_details_temperature_day).toString())

                    TextDetails(text = item.low_temp.toInt().toString(),
                        degree = textResource(id =
                        R.string.label_weather_details_degree_temperature).toString(),
                        textLabel = textResource(id =
                        R.string.label_weather_details_temperature_night).toString())

                    TextDetails(text = item.app_max_temp.toInt().toString(),
                        degree = textResource(id =
                        R.string.label_weather_details_degree_temperature).toString(),
                        textLabel = textResource(id =
                        R.string.label_weather_details_temperature_day_feel_like).toString())

                    TextDetails(text = item.app_min_temp.toInt().toString(),
                        degree = textResource(id =
                        R.string.label_weather_details_degree_temperature).toString(),
                        textLabel = textResource(id =
                        R.string.label_weather_details_temperature_night_feel_like).toString())

                    TextDetails(text = item.rh.toString(),
                        degree = textResource(id =
                        R.string.label_weather_details_degree_humidity).toString(),
                        textLabel = textResource(id =
                        R.string.label_weather_details_humidity).toString())

                    TextDetails(text = item.pres.toInt().toString(),
                        degree = textResource(id =
                        R.string.label_weather_details_degree_pressure).toString(),
                        textLabel = textResource(id =
                        R.string.label_weather_details_pressure).toString())

                    TextDetails(text = item.uv.toInt().toString(),
                        degree ="",
                        textLabel = textResource(id =
                        R.string.label_weather_details_uv_index).toString())

                    location?.let {
                        TextDetails(text = formatTime(item.sunrise_ts.toLong()),
                            degree = "",
                            textLabel = textResource(id =
                            R.string.label_weather_details_sunrise_time).toString())

                        TextDetails(text = formatTime(item.sunset_ts.toLong()),
                            degree = "",
                            textLabel = textResource(id =
                            R.string.label_weather_details_sunset_time).toString())
                    }

                }
            }
        }
    }

}

@Composable
fun TextDetails(
    text: String,
    degree: String,
    textLabel: String
) {
    Row() {
        Text(
            text = textLabel,
            color = AppTheme.colors.subtitleTextColor,
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 2.dp,
                    top = 2.dp,
                    bottom = 16.dp
                ),
            fontSize = 16.sp
        )
        Text(
            text = "$text $degree",
            color = AppTheme.colors.headerTextColor,
            modifier = Modifier
                .padding(
                    start = 4.dp,
                    end = 2.dp,
                    top = 2.dp,
                    bottom = 16.dp
                ),
            fontSize = 16.sp
        )
    }
}