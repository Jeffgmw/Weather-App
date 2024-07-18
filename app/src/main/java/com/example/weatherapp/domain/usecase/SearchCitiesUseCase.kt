package com.example.weatherapp.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.weatherapp.data.entity.CitiesForSearchEntity
import com.example.weatherapp.data.repository.SearchCitiesRepository
import com.example.weatherapp.utils.UseCaseLiveData
import com.example.weatherapp.utils.Resource
import com.example.weatherapp.presentation.ui.search.SearchViewState
import javax.inject.Inject

class SearchCitiesUseCase @Inject internal constructor(
    private val repository: SearchCitiesRepository
) : UseCaseLiveData<SearchViewState, SearchCitiesUseCase.SearchCitiesParams, SearchCitiesRepository>() {

    override fun getRepository(): SearchCitiesRepository = repository

    override fun buildUseCaseObservable(params: SearchCitiesParams?): LiveData<SearchViewState> {
        return repository.loadCitiesByCityName(
            cityName = params?.city ?: ""
        ).map {
            onSearchResultReady(it)
        }
    }

    private fun onSearchResultReady(resource: Resource<List<CitiesForSearchEntity>>): SearchViewState {
        return SearchViewState(
            status = resource.status,
            error = resource.message,
            data = resource.data
        )
    }

    class SearchCitiesParams(
        val city: String = ""
    ) : Params()
}
