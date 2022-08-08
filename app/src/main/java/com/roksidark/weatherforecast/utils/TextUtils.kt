package com.roksidark.weatherforecast.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

const val TIME_FORMAT = "HH:mm"

fun formatDate(date: String): String{
    return LocalDate.parse(date).dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH) + ", "+
            LocalDate.parse(date).dayOfMonth.toString() + " "+
            LocalDate.parse(date).month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH).lowercase()
}

fun formatDateFull(date: String): String{
    return LocalDate.parse(date).dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH) + ", "+
            LocalDate.parse(date).dayOfMonth.toString() + " "+
            LocalDate.parse(date).month.getDisplayName(TextStyle.FULL, Locale.ENGLISH).lowercase()
}

fun formatTime(timeInMillis: Long): String {
    var time = Date(timeInMillis * 1000)
    val dateFormat = SimpleDateFormat(TIME_FORMAT, Locale.getDefault())
    return dateFormat.format(time)
}
