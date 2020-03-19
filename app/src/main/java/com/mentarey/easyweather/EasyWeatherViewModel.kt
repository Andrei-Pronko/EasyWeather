package com.mentarey.easyweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.mentarey.easyweather.data.pref.LastWeatherRepository
import com.mentarey.easyweather.data.weather.WeatherApi
import com.mentarey.easyweather.data.weather.model.current.WeatherNow
import com.mentarey.easyweather.ui.state.WeatherLoadingState
import com.mentarey.easyweather.utils.toWeatherNow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EasyWeatherViewModel(
    private val lastWeatherRepository: LastWeatherRepository,
    private val weatherApi: WeatherApi,
    private val gson: Gson
) : ViewModel() {

    private val _loading: MutableLiveData<WeatherLoadingState> =
        MutableLiveData(WeatherLoadingState.Empty)
    val loading: LiveData<WeatherLoadingState> = _loading

    private val _weatherNow: MutableLiveData<WeatherNow> = MutableLiveData(WeatherNow())
    val weatherNow: LiveData<WeatherNow> = _weatherNow

    private val _lastCity: MutableLiveData<String> = MutableLiveData("")
    val lastCity: LiveData<String> = _lastCity

    init {
        loadLastWeather()
        loadLastCity()
    }

    fun getWeatherInTheCity(city: String) {
        saveLastCity(city)
        viewModelScope.launch {
            _loading.value = WeatherLoadingState.Loading
            val result = runCatching {
                weatherApi.getCurrentWeatherAsync(city).await()
            }
            result
                .onSuccess {
                    val json = gson.toJson(it)
                    lastWeatherRepository.setCurrentWeather(json)
                    _weatherNow.value = it.toWeatherNow()
                    _loading.value = WeatherLoadingState.Success
                }.onFailure {
                    _loading.value = WeatherLoadingState.Error(it.message.toString())
                }
        }
    }

    private fun loadLastWeather() {
        viewModelScope.launch {
            _loading.value = WeatherLoadingState.Loading
            val lastWeather = withContext(Dispatchers.IO) {
                lastWeatherRepository.getCurrentWeather()
            }
            _weatherNow.value = lastWeather
            _loading.value = WeatherLoadingState.Success
        }
    }

    private fun loadLastCity() {
        viewModelScope.launch {
            val lastCity = withContext(Dispatchers.IO) {
                lastWeatherRepository.getLastWeatherCity()
            }
            _lastCity.value = lastCity
        }
    }

    fun saveLastCity(city: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) { lastWeatherRepository.setLastWeatherCity(city) }
        }
    }
}