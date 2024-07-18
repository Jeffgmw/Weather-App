package com.example.weatherapp.di

import com.example.weatherapp.data.repository.CurrentWeatherRepository
import com.example.weatherapp.data.repository.ForecastRepository
import com.example.weatherapp.data.repository.SearchCitiesRepository
import com.example.weatherapp.domain.datasource.currentWeather.CurrentWeatherLocalDataSource
import com.example.weatherapp.domain.datasource.currentWeather.CurrentWeatherRemoteDataSource
import com.example.weatherapp.domain.datasource.forecast.ForecastLocalDataSource
import com.example.weatherapp.domain.datasource.forecast.ForecastRemoteDataSource
import com.example.weatherapp.domain.datasource.searchCities.SearchCitiesLocalDataSource
import com.example.weatherapp.domain.datasource.searchCities.SearchCitiesRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCurrentWeatherRepository(
        currentWeatherRemoteDataSource: CurrentWeatherRemoteDataSource,
        currentWeatherLocalDataSource: CurrentWeatherLocalDataSource,
    ) = CurrentWeatherRepository(currentWeatherRemoteDataSource, currentWeatherLocalDataSource)

    @Provides
    @Singleton
    fun provideForecastRepository(
        forecastRemoteDataSource: ForecastRemoteDataSource,
        forecastLocalDataSource: ForecastLocalDataSource,
    ) = ForecastRepository(forecastRemoteDataSource, forecastLocalDataSource)

    @Provides
    @Singleton
    fun provideSearchCitiesRepository(
        searchCitiesRemoteDataSource: SearchCitiesRemoteDataSource,
        searchCitiesLocalDataSource: SearchCitiesLocalDataSource,
    ) = SearchCitiesRepository(searchCitiesRemoteDataSource, searchCitiesLocalDataSource)

}