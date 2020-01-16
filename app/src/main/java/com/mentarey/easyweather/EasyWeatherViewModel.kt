package com.mentarey.easyweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mentarey.easyweather.ui.model.WeatherLoadingState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EasyWeatherViewModel : ViewModel() {
    private val _loading: MutableLiveData<WeatherLoadingState> =
        MutableLiveData(WeatherLoadingState.Success)
    val loading: LiveData<WeatherLoadingState> = _loading

    fun retryWeatherLoading() {
        viewModelScope.launch {
            _loading.value = WeatherLoadingState.Loading
            delay(2000)
            _loading.value = WeatherLoadingState.Success
        }
    }

    fun updateWeather() {
        viewModelScope.launch {
            _loading.value = WeatherLoadingState.Loading
            delay(2000)
            _loading.value = WeatherLoadingState.Error("Internet not available")
        }
    }
}