package com.roksidark.weatherforecast.di.module

import androidx.lifecycle.ViewModel
import com.roksidark.weatherforecast.ui.screens.location.LocationViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap

import kotlin.reflect.KClass

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LocationViewModel::class)
    abstract fun bindMainViewModel(locationViewModel: LocationViewModel): ViewModel

}

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)