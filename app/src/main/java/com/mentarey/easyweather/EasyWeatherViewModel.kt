package com.mentarey.easyweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mentarey.easyweather.data.weather.WeatherClient
import com.mentarey.easyweather.data.weather.model.current.WeatherNow
import com.mentarey.easyweather.ui.model.WeatherLoadingState
import com.mentarey.easyweather.ui.model.WeatherType
import com.mentarey.easyweather.utils.toWeatherNow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class EasyWeatherViewModel : ViewModel() {
    private val _loading: MutableLiveData<WeatherLoadingState> =
        MutableLiveData(WeatherLoadingState.Success)
    val loading: LiveData<WeatherLoadingState> = _loading

    private val _weatherNow: MutableLiveData<WeatherNow> = MutableLiveData(WeatherNow())
    val weatherNow: LiveData<WeatherNow> = _weatherNow

    fun getWeatherInTheCity(city: String = "Брест") {
        viewModelScope.launch {
            _loading.value = WeatherLoadingState.Loading
            val result = withContext(Dispatchers.IO) {
                val api = WeatherClient.weatherApi
                kotlin.runCatching {
                    api.getCurrentWeatherAsync(city, "52.1", "23.7").await()
                }
            }
            result
                .onSuccess {
                    _weatherNow.value = it.toWeatherNow()
                    _loading.value = WeatherLoadingState.Success
                }.onFailure {
                    _loading.value = WeatherLoadingState.Error(it.message.toString())
                }
        }
    }
}