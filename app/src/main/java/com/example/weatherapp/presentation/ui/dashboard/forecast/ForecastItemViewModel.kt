package com.example.weatherapp.presentation.ui.dashboard.forecast

import androidx.databinding.ObservableField
import com.example.weatherapp.domain.model.ListItem
import com.example.weatherapp.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForecastItemViewModel @Inject internal constructor() : BaseViewModel() {
    var item = ObservableField<ListItem>()
}
