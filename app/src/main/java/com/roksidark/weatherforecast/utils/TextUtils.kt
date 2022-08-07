package com.roksidark.weatherforecast.utils

import android.util.Log
import com.roksidark.weatherforecast.utils.Constant.TAG
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*


fun getDayOfWeek(date: String): String =
    LocalDate.parse(date).dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)


fun getTime(zone: String, time: Long): String{

    Log.d(TAG, "get time: "+time)

    val date = Date(time)
    var sdf = SimpleDateFormat("h:mm", Locale.ENGLISH)
    sdf.timeZone = TimeZone.getTimeZone(zone)

    Log.d(TAG, "set time: "+sdf.format(date))
    return  sdf.format(date)
}