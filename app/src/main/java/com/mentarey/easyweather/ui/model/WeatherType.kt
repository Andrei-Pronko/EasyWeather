package com.mentarey.easyweather.ui.model

sealed class WeatherType {
    object Clear : WeatherType()
    object Cloud : WeatherType()
    object Sun : WeatherType()
    object Rain : WeatherType()
    object Show : WeatherType()
    object Fog : WeatherType()
}