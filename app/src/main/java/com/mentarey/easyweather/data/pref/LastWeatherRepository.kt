package com.mentarey.easyweather.data.pref

import com.mentarey.easyweather.data.weather.model.current.WeatherNow

interface LastWeatherRepository {
    suspend fun setCurrentWeather(json: String)
    suspend fun getCurrentWeather(): WeatherNow
    suspend fun getLastWeatherCity(): String
    suspend fun setLastWeatherCity(city: String)
}