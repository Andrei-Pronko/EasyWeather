package com.mentarey.easyweather.ui.model

sealed class WeatherLoadingState {
    object Loading : WeatherLoadingState()
    class Error(val reason: String) : WeatherLoadingState()
    object Success : WeatherLoadingState()
}