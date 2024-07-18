package com.example.weatherapp.presentation.ui.dashboard

import com.example.weatherapp.presentation.ui.base.BaseViewState
import com.example.weatherapp.data.entity.CurrentWeatherEntity
import com.example.weatherapp.utils.Status

class CurrentWeatherViewState(
    val status: Status,
    val error: String? = null,
    val data: CurrentWeatherEntity? = null
) : BaseViewState(status, error) {
    fun getForecast() = data
}
