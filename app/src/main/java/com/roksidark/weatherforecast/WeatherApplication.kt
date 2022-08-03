package com.roksidark.weatherforecast

import com.roksidark.weatherforecast.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class WeatherApplication: DaggerApplication() {
    private val applicationInjector = DaggerAppComponent
        .builder()
        .application(this)
        .build()
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = applicationInjector
}