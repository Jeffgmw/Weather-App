package com.example.weatherapp.domain.datasource.searchCities

import androidx.lifecycle.LiveData
import com.example.weatherapp.data.local.dao.CitiesForSearchDao
import com.example.weatherapp.data.entity.CitiesForSearchEntity
import com.example.weatherapp.data.remote.Response.SearchResponse
import javax.inject.Inject

class SearchCitiesLocalDataSource @Inject constructor(
    private val citiesForSearchDao: CitiesForSearchDao
) {

    fun getCityByName(cityName: String?): LiveData<List<CitiesForSearchEntity>> = citiesForSearchDao.getCityByName(
        cityName
    )

    fun insertCities(response: SearchResponse) {
        response.hits
            ?.map { CitiesForSearchEntity(it) }
            ?.let { citiesForSearchDao.insertCities(it) }
    }
}
