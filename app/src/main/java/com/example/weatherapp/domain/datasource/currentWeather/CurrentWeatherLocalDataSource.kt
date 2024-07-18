package com.example.weatherapp.domain.datasource.currentWeather

import com.example.weatherapp.data.local.dao.CurrentWeatherDao
import com.example.weatherapp.data.entity.CurrentWeatherEntity
import com.example.weatherapp.data.remote.Response.CurrentWeatherResponse
import javax.inject.Inject

class CurrentWeatherLocalDataSource @Inject constructor(
    private val currentWeatherDao: CurrentWeatherDao
) {

    fun getCurrentWeather() = currentWeatherDao.getCurrentWeather()

    fun insertCurrentWeather(currentWeather: CurrentWeatherResponse) = currentWeatherDao.deleteAndInsert(
        CurrentWeatherEntity(currentWeather)
    )
}
