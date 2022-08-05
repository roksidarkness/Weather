package com.roksidark.weatherforecast.ui.theme

import androidx.compose.ui.graphics.Color

data class Colors(
    val primaryBackground: Color,
    val secondaryBackground: Color,
    val headerTextColor: Color,
    val primaryTextColor: Color,
    val backgroundColor: Color
)

val lightPalette = Colors(
    primaryBackground = Color.White,
    secondaryBackground = Color(0XFFABCBFF),
    headerTextColor = Color(0xFF3AA4E0),
    primaryTextColor = Color(0xFF014A73),
    backgroundColor = Color(0XFFC0D7FC)
)