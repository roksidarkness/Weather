package com.roksidark.weatherforecast.di.module

import android.app.Application
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import com.roksidark.weatherforecast.feature_forecast.data.repository.RemoteRepositoryImpl
import com.roksidark.weatherforecast.feature_forecast.data.rest.WeatherApi
import com.roksidark.weatherforecast.feature_forecast.domain.repository.RemoteRepository
import com.roksidark.weatherforecast.feature_forecast.domain.usecase.GetWeatherRemotely
import com.roksidark.weatherforecast.feature_forecast.domain.usecase.WeatherUseCases
import com.roksidark.weatherforecast.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constant.BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): WeatherApi = retrofit.create(WeatherApi::class.java)

    @Singleton
    @Provides
    fun providesRepository(weatherApi: WeatherApi): RemoteRepository =
        RemoteRepositoryImpl(weatherApi)

    @Singleton
    @Provides
    fun provideWeatherUseCase(remoteRepository: RemoteRepository): WeatherUseCases {
        return WeatherUseCases(
            getWeatherForecastRemotely = GetWeatherRemotely(remoteRepository),
        )
    }

    @Provides
    @Singleton
    fun providePlaceClient(application: Application): PlacesClient =
        Places.createClient(application.applicationContext)
}