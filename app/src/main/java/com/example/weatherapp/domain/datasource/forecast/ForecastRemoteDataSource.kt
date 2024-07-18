package com.example.weatherapp.domain.datasource.forecast

import com.example.weatherapp.data.remote.Response.ForecastResponse
import com.example.weatherapp.data.remote.WeatherAppAPI
import io.reactivex.Single
import javax.inject.Inject

class ForecastRemoteDataSource @Inject constructor(private val api: WeatherAppAPI) {

    fun getForecastByGeoCords(lat: Double, lon: Double, units: String): Single<ForecastResponse> = api.getForecastByGeoCords(
        lat,
        lon,
        units
    )
}
