package com.example.weatherapp.di

import com.example.weatherapp.data.repository.CurrentWeatherRepository
import com.example.weatherapp.data.repository.ForecastRepository
import com.example.weatherapp.data.repository.SearchCitiesRepository
import com.example.weatherapp.domain.usecase.CurrentWeatherUseCase
import com.example.weatherapp.domain.usecase.ForecastUseCase
import com.example.weatherapp.domain.usecase.SearchCitiesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    @Provides
    @Singleton
    fun provideCurrentWeatherUseCase(currentWeatherRepository: CurrentWeatherRepository) =
        CurrentWeatherUseCase(currentWeatherRepository)

    @Provides
    @Singleton
    fun provideForecastUseCase(forecastRepository: ForecastRepository) =
        ForecastUseCase(forecastRepository)

    @Provides
    @Singleton
    fun provideSearchCitiesUseCase(searchCitiesRepository: SearchCitiesRepository) =
        SearchCitiesUseCase(searchCitiesRepository)

}