package com.roksidark.weatherforecast.di.component

import com.roksidark.weatherforecast.WeatherApplication
import com.roksidark.weatherforecast.di.module.AppModule
import com.roksidark.weatherforecast.di.module.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        MainActivityModule::class
    ]
)
interface AppComponent : AndroidInjector<WeatherApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: WeatherApplication): Builder

        fun build(): AppComponent

    }

    override fun inject(app: WeatherApplication)
}