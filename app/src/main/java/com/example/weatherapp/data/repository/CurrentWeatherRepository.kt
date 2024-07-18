package com.example.weatherapp.data.repository

import androidx.lifecycle.LiveData
import com.example.weatherapp.data.entity.CurrentWeatherEntity
import com.example.weatherapp.data.remote.Response.CurrentWeatherResponse
import com.example.weatherapp.utils.Constants.NetworkService.RATE_LIMITER_TYPE
import com.example.weatherapp.utils.RateLimiter
import com.example.weatherapp.utils.Resource
import com.example.weatherapp.domain.datasource.currentWeather.CurrentWeatherLocalDataSource
import com.example.weatherapp.domain.datasource.currentWeather.CurrentWeatherRemoteDataSource
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CurrentWeatherRepository @Inject constructor(
    private val currentWeatherRemoteDataSource: CurrentWeatherRemoteDataSource,
    private val currentWeatherLocalDataSource: CurrentWeatherLocalDataSource
) {

    private val currentWeatherRateLimit = RateLimiter<String>(30, TimeUnit.SECONDS)

    fun loadCurrentWeatherByGeoCords(
        lat: Double,
        lon: Double,
        fetchRequired: Boolean,
        units: String
    ): LiveData<Resource<CurrentWeatherEntity>> {
        return object : NetworkBoundResource<CurrentWeatherEntity, CurrentWeatherResponse>() {
            override fun saveCallResult(item: CurrentWeatherResponse) = currentWeatherLocalDataSource.insertCurrentWeather(
                item
            )

            override fun shouldFetch(data: CurrentWeatherEntity?): Boolean = fetchRequired

            override fun loadFromDb(): LiveData<CurrentWeatherEntity> = currentWeatherLocalDataSource.getCurrentWeather()

            override fun createCall(): Single<CurrentWeatherResponse> = currentWeatherRemoteDataSource.getCurrentWeatherByGeoCords(
                lat,
                lon,
                units
            )

            override fun onFetchFailed() = currentWeatherRateLimit.reset(RATE_LIMITER_TYPE)
        }.asLiveData
    }
}
