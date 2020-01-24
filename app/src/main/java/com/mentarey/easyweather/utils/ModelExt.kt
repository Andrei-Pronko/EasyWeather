package com.mentarey.easyweather.utils

import com.mentarey.easyweather.data.weather.model.api.WeatherInfo
import com.mentarey.easyweather.data.weather.model.current.*
import com.mentarey.easyweather.utils.SystemInfoUtils.getStringTime

fun WeatherInfo.toWeatherNow(): WeatherNow =
    WeatherNow(
        city = name,
        currentTime = getStringTime(SystemInfoUtils.nowTimeMillis),
        temperature = Temperature(
            current = main.temp,
            min = main.tempMin,
            max = main.tempMax,
            feelsLike = main.feels_like
        ),
        currentWeather = CurrentWeather(
            forecast = weather[0].main,
            forecastWithLocale = weather[0].description
        ),
        dayDuration = DayDuration(
            sunrise = sys.sunrise,
            sunset = sys.sunset
        ),
        wind = Wind(
            speed = wind.speed,
            degree = wind.deg,
            gust = wind.gust
        ),
        extraOptions = ExtraOptions(
            cloudiness = clouds.all,
            pressure = (main.pressure / 1.333).toInt(),
            humidity = main.humidity,
            visibility = (visibility / 1000).toDouble()
        )
    )