package com.example.weatherapp.presentation.ui.search.result

import androidx.databinding.ObservableField
import com.example.weatherapp.presentation.ui.base.BaseViewModel
import com.example.weatherapp.data.entity.CitiesForSearchEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchResultItemViewModel @Inject internal constructor() : BaseViewModel() {
    var item = ObservableField<CitiesForSearchEntity>()
}
