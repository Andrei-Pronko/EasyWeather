package com.mentarey.easyweather.utils

import com.mentarey.easyweather.data.weather.model.WeatherInfo
import com.mentarey.easyweather.data.weather.model.current.CurrentWeather
import com.mentarey.easyweather.data.weather.model.current.Temperature
import com.mentarey.easyweather.data.weather.model.current.WeatherNow
import com.mentarey.easyweather.utils.SystemInfoUtils.getStringTime

fun WeatherInfo.toWeatherNow(): WeatherNow =
    WeatherNow(
        city = name,
        lastWeatherUpdate = getStringTime(dt),
        temperature = Temperature(
            current = main.temp,
            min = main.tempMin,
            max = main.tempMax,
            feelsLike = main.feels_like
        ),
        currentWeather = CurrentWeather(
            forecast = weather[0].main,
            forecastWithLocale = weather[0].description
        )
    )