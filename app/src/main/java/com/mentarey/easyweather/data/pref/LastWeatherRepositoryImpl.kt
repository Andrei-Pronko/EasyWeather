package com.mentarey.easyweather.data.pref

import com.google.gson.Gson
import com.mentarey.easyweather.data.weather.model.api.WeatherInfo
import com.mentarey.easyweather.data.weather.model.current.WeatherNow
import com.mentarey.easyweather.utils.toWeatherNow

class LastWeatherRepositoryImpl(
    private val prefsManager: PrefsManager,
    private val gson: Gson
) :
    LastWeatherRepository {

    override suspend fun setCurrentWeather(json: String) {
        prefsManager.putStringValueByKey(CURRENT_WEATHER_KEY, json)
    }

    override suspend fun getCurrentWeather(): WeatherNow {
        val currentWeather = prefsManager.getStringValue(CURRENT_WEATHER_KEY, EMPTY_LINE)
        return when (currentWeather.isEmpty()) {
            true -> WeatherNow()
            false -> {
                val weatherInfo = gson.fromJson(currentWeather, WeatherInfo::class.java)
                weatherInfo.toWeatherNow()
            }
        }
    }

    override suspend fun getLastWeatherCity(): String =
        prefsManager.getStringValue(CURRENT_CITY_KEY, "Брест")

    override suspend fun setLastWeatherCity(city: String) =
        prefsManager.putStringValueByKey(CURRENT_CITY_KEY, city)

    companion object {
        const val CURRENT_WEATHER_KEY = "CURRENT_WEATHER_KEY"
        const val CURRENT_CITY_KEY = "CURRENT_CITY_KEY"
        const val EMPTY_LINE = ""
    }
}