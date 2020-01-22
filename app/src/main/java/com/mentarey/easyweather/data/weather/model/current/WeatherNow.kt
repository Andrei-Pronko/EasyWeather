package com.mentarey.easyweather.data.weather.model.current

import com.mentarey.easyweather.R
import com.mentarey.easyweather.utils.SystemInfoUtils.getStringTime
import com.mentarey.easyweather.utils.SystemInfoUtils.nowTimeMillis

data class WeatherNow(
    val city: String = "Брест",
    val currentTime: String = getStringTime(nowTimeMillis),
    val lastWeatherUpdate: String = getStringTime(nowTimeMillis),
    val temperature: Temperature = Temperature(),
    val currentWeather: CurrentWeather = CurrentWeather(),
    val dayDuration: DayDuration = DayDuration(),
    val clouds: Clouds = Clouds(),
    val wind: Wind = Wind()
)

/**
 * Main
 */

data class CurrentWeather(
    val forecast: String = "Clouds",                // текущий прогноз
    val forecastWithLocale: String = "облачно"     // текущий прогноз на языке системы
)

fun CurrentWeather.getBackgroundResId(): Int = when (forecast) {
    "Clouds" -> R.drawable.bg_clouds
    "Rain" -> R.drawable.bg_rain
    "Sun" -> R.drawable.bg_sun
    else -> R.drawable.bg_default
}

data class DayDuration(
    val sunrise: String = "08:00",
    val sunset: String = "18:00"
)

data class Temperature(
    val current: Double = -1.9,
    val min: Double = -5.6,
    val max: Double = 3.1,
    val feelsLike: Double = -5.0
)

/**
 * Additional
 */
data class Clouds(
    val cloudiness: Int = 90 // процент покрутия облаками - чем выше, чем больше облаков
)

data class Wind(
    val speed: Double = 10.0,  // скорость вертра
    val gust: Double = 15.0,   // максимальные порывы
    val degree: Int = 0,    // направление (o)
    val scaleValue: Double = 0.2 // относительное значение
)

data class Pressure(
    val pressure: Int = 760 // давление в мм. рт. ст.
)