package com.example.weatherapp.data.repository

import androidx.lifecycle.LiveData
import com.example.weatherapp.data.entity.ForecastEntity
import com.example.weatherapp.data.remote.Response.ForecastResponse
import com.example.weatherapp.utils.Constants.NetworkService.RATE_LIMITER_TYPE
import com.example.weatherapp.utils.RateLimiter
import com.example.weatherapp.utils.Resource
import com.example.weatherapp.domain.datasource.forecast.ForecastLocalDataSource
import com.example.weatherapp.domain.datasource.forecast.ForecastRemoteDataSource
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class ForecastRepository @Inject constructor(
    private val forecastRemoteDataSource: ForecastRemoteDataSource,
    private val forecastLocalDataSource: ForecastLocalDataSource
) {

    private val forecastListRateLimit = RateLimiter<String>(30, TimeUnit.SECONDS)

    fun loadForecastByCoord(lat: Double, lon: Double, fetchRequired: Boolean, units: String): LiveData<Resource<ForecastEntity>> {
        return object : NetworkBoundResource<ForecastEntity, ForecastResponse>() {
            override fun saveCallResult(item: ForecastResponse) = forecastLocalDataSource.insertForecast(
                item
            )

            override fun shouldFetch(data: ForecastEntity?): Boolean = fetchRequired

            override fun loadFromDb(): LiveData<ForecastEntity> = forecastLocalDataSource.getForecast()

            override fun createCall(): Single<ForecastResponse> = forecastRemoteDataSource.getForecastByGeoCords(
                lat,
                lon,
                units
            )

            override fun onFetchFailed() = forecastListRateLimit.reset(RATE_LIMITER_TYPE)
        }.asLiveData
    }
}
