package com.example.weatherapp.presentation.ui.search

import com.example.weatherapp.presentation.ui.base.BaseViewState
import com.example.weatherapp.data.entity.CitiesForSearchEntity
import com.example.weatherapp.utils.Status

class SearchViewState(
    val status: Status,
    val error: String? = null,
    val data: List<CitiesForSearchEntity>? = null
) : BaseViewState(status, error) {
    fun getSearchResult() = data
}
