package com.roksidark.weatherforecast.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import com.roksidark.weatherforecast.WeatherApplication
import com.roksidark.weatherforecast.feature_forecast.data.db.WeatherDatabase
import com.roksidark.weatherforecast.feature_forecast.data.repository.LocalRepositoryImpl
import com.roksidark.weatherforecast.feature_forecast.data.repository.RemoteRepositoryImpl
import com.roksidark.weatherforecast.feature_forecast.data.rest.WeatherApi
import com.roksidark.weatherforecast.feature_forecast.domain.repository.LocalRepository
import com.roksidark.weatherforecast.feature_forecast.domain.repository.RemoteRepository
import com.roksidark.weatherforecast.feature_forecast.domain.usecase.*
import com.roksidark.weatherforecast.utils.Constant
import com.roksidark.weatherforecast.utils.Constant.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): WeatherDatabase =
        Room.databaseBuilder(
            context, WeatherDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideLocationRepository(database: WeatherDatabase): LocalRepository =
        LocalRepositoryImpl(database)

    @Singleton
    @Provides
    fun provideWeatherUseCase(remoteRepository: RemoteRepository, localRepository: LocalRepository): WeatherUseCases {
        return WeatherUseCases(
            getWeatherForecastRemotely = GetWeatherRemotely(remoteRepository),
            saveLocationLocal = SaveLocationLocal(localRepository),
            getLocationsLocal = GetLocationsLocal(localRepository),
            getLocationLocal = GetLocationLocal(localRepository)
        )
    }

    @Provides
    @Singleton
    fun providePlaceClient(application: Application): PlacesClient =
        Places.createClient(application.applicationContext)

    @Singleton
    @Provides
    fun provideApplicationContext() = WeatherApplication()
}