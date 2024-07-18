package com.example.weatherapp.domain.datasource.forecast

import com.example.weatherapp.data.local.dao.ForecastDao
import com.example.weatherapp.data.entity.ForecastEntity
import com.example.weatherapp.data.remote.Response.ForecastResponse
import javax.inject.Inject


class ForecastLocalDataSource @Inject constructor(private val forecastDao: ForecastDao) {

    fun getForecast() = forecastDao.getForecast()

    fun insertForecast(forecast: ForecastResponse) = forecastDao.deleteAndInsert(
        ForecastEntity(forecast)
    )
}
