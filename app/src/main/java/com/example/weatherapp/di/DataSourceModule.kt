package com.example.weatherapp.di

import com.algolia.search.saas.places.PlacesClient
import com.example.weatherapp.data.local.dao.CitiesForSearchDao
import com.example.weatherapp.data.local.dao.CurrentWeatherDao
import com.example.weatherapp.data.local.dao.ForecastDao
import com.example.weatherapp.data.remote.WeatherAppAPI
import com.example.weatherapp.domain.datasource.currentWeather.CurrentWeatherLocalDataSource
import com.example.weatherapp.domain.datasource.currentWeather.CurrentWeatherRemoteDataSource
import com.example.weatherapp.domain.datasource.forecast.ForecastLocalDataSource
import com.example.weatherapp.domain.datasource.forecast.ForecastRemoteDataSource
import com.example.weatherapp.domain.datasource.searchCities.SearchCitiesLocalDataSource
import com.example.weatherapp.domain.datasource.searchCities.SearchCitiesRemoteDataSource
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {

    @Provides
    @Singleton
    fun provideCurrentWeatherRemoteDataSource(api: WeatherAppAPI) =
        CurrentWeatherRemoteDataSource(api)

    @Provides
    @Singleton
    fun provideForecastRemoteDataSource(api: WeatherAppAPI) =
        ForecastRemoteDataSource(api)

    @Provides
    @Singleton
    fun provideSearchCitiesRemoteDataSource(
        client: PlacesClient,
        moshi: Moshi,
    ) = SearchCitiesRemoteDataSource(client, moshi)

    @Provides
    @Singleton
    fun provideCurrentWeatherLocalDataSource(currentWeatherDao: CurrentWeatherDao) =
        CurrentWeatherLocalDataSource(currentWeatherDao)

    @Provides
    @Singleton
    fun provideForecastLocalDataSource(forecastDao: ForecastDao) =
        ForecastLocalDataSource(forecastDao)

    @Provides
    @Singleton
    fun provideSearchCitiesLocalDataSource(citiesForSearchDao: CitiesForSearchDao) =
        SearchCitiesLocalDataSource(citiesForSearchDao)

}