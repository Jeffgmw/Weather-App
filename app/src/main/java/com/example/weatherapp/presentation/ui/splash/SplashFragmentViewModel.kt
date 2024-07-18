package com.example.weatherapp.presentation.ui.splash

import android.content.SharedPreferences
import com.example.weatherapp.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashFragmentViewModel @Inject constructor(
    var sharedPreferences: SharedPreferences
) : BaseViewModel() {
    var navigateDashboard = false
}
