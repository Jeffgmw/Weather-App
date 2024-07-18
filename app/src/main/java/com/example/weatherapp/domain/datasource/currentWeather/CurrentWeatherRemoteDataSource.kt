package com.example.weatherapp.domain.datasource.currentWeather

import com.example.weatherapp.data.remote.Response.CurrentWeatherResponse
import com.example.weatherapp.data.remote.WeatherAppAPI
import io.reactivex.Single
import javax.inject.Inject

class CurrentWeatherRemoteDataSource @Inject constructor(private val api: WeatherAppAPI) {

    fun getCurrentWeatherByGeoCords(lat: Double, lon: Double, units: String): Single<CurrentWeatherResponse> = api.getCurrentByGeoCords(
        lat,
        lon,
        units
    )
}
