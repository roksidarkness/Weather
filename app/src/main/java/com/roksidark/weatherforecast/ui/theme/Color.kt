package com.roksidark.weatherforecast.ui.theme

import androidx.compose.ui.graphics.Color

data class Colors(
    val primaryBackground: Color,
    val secondaryBackground: Color,
    val headerTextColor: Color,
    val subtitleTextColor: Color,
    val primaryTextColor: Color,
    val backgroundColor: Color
)

val lightPalette = Colors(
    primaryBackground = Color.White,
    secondaryBackground = Color(0XFFABCBFF),
    headerTextColor = Color(0xFF505B5E),
    subtitleTextColor = Color(0xFF98ACB3),
    primaryTextColor = Color(0xFF345159),
    backgroundColor = Color(0XFFC0D7FC)
)