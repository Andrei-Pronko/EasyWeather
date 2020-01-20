package com.mentarey.easyweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mentarey.easyweather.data.weather.WeatherClient
import com.mentarey.easyweather.ui.model.WeatherLoadingState
import com.mentarey.easyweather.ui.model.WeatherType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class EasyWeatherViewModel : ViewModel() {
    private val _loading: MutableLiveData<WeatherLoadingState> =
        MutableLiveData(WeatherLoadingState.Success)
    val loading: LiveData<WeatherLoadingState> = _loading

    private val _weatherType: MutableLiveData<WeatherType> =
        MutableLiveData(WeatherType.Sun)
    val weatherType: LiveData<WeatherType> = _weatherType

    private val _weatherInfo: MutableLiveData<String> = MutableLiveData("")
    val weatherInfo: LiveData<String> = _weatherInfo

    fun retryWeatherLoading() {
        viewModelScope.launch {
            _loading.value = WeatherLoadingState.Loading
            delay(2000)
            _loading.value = WeatherLoadingState.Success
            _weatherType.value = WeatherType.Rain
        }
    }

    fun updateWeather() {
        viewModelScope.launch {
            _loading.value = WeatherLoadingState.Loading
            delay(2000)
            _loading.value = WeatherLoadingState.Error("Internet not available")
            _weatherType.value = WeatherType.Sun
        }
    }

    fun getWeatherData() {
        viewModelScope.launch {
            _loading.value = WeatherLoadingState.Loading
            val result = withContext(Dispatchers.IO) {
                val api = WeatherClient.weatherApi
                api.getWeatherInfoAsync().await()
            }
            _weatherInfo.value = result.toString()
            _weatherType.value = WeatherType.Sun
            _loading.value = WeatherLoadingState.Success
        }
    }
}