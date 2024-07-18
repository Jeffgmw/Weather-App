package com.example.weatherapp.presentation.ui.dashboard

import com.example.weatherapp.presentation.ui.base.BaseViewState
import com.example.weatherapp.data.entity.ForecastEntity
import com.example.weatherapp.utils.Status

class ForecastViewState(
    val status: Status,
    val error: String? = null,
    val data: ForecastEntity? = null
) : BaseViewState(status, error) {
    fun getForecast() = data
}
