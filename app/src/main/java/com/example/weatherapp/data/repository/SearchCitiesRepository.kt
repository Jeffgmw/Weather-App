package com.example.weatherapp.data.repository

import androidx.lifecycle.LiveData
import com.example.weatherapp.data.entity.CitiesForSearchEntity
import com.example.weatherapp.data.remote.Response.SearchResponse
import com.example.weatherapp.utils.Constants.NetworkService.RATE_LIMITER_TYPE
import com.example.weatherapp.utils.RateLimiter
import com.example.weatherapp.utils.Resource
import com.example.weatherapp.domain.datasource.searchCities.SearchCitiesLocalDataSource
import com.example.weatherapp.domain.datasource.searchCities.SearchCitiesRemoteDataSource
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class SearchCitiesRepository @Inject constructor(
    private val searchCitiesRemoteDataSource: SearchCitiesRemoteDataSource,
    private val searchCitiesLocalDataSource: SearchCitiesLocalDataSource,
) {

    private val rateLimiter = RateLimiter<String>(1, TimeUnit.SECONDS)

    fun loadCitiesByCityName(cityName: String?): LiveData<Resource<List<CitiesForSearchEntity>>> {
        return object : NetworkBoundResource<List<CitiesForSearchEntity>, SearchResponse>() {
            override fun saveCallResult(item: SearchResponse) = searchCitiesLocalDataSource.insertCities(
                item
            )

            override fun shouldFetch(data: List<CitiesForSearchEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<CitiesForSearchEntity>> = searchCitiesLocalDataSource.getCityByName(
                cityName
            )

            override fun createCall(): Single<SearchResponse> = searchCitiesRemoteDataSource.getCityWithQuery(
                cityName
                    ?: ""
            )

            override fun onFetchFailed() = rateLimiter.reset(RATE_LIMITER_TYPE)
        }.asLiveData
    }
}
