package com.mentarey.easyweather.ui.state

sealed class WeatherLoadingState {
    object Loading : WeatherLoadingState()
    class Error(val reason: String) : WeatherLoadingState()
    object Success : WeatherLoadingState()
    object Empty : WeatherLoadingState()
}