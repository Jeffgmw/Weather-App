package com.example.weatherapp.presentation.ui.weather_detail.weatherHourOfDay

import androidx.databinding.ObservableField
import com.example.weatherapp.domain.model.ListItem
import com.example.weatherapp.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class WeatherHourOfDayItemViewModel @Inject internal constructor() : BaseViewModel() {
    var item = ObservableField<ListItem>()
}
