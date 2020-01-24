package com.mentarey.easyweather.data.weather.model.current

import com.mentarey.easyweather.R
import com.mentarey.easyweather.utils.SystemInfoUtils.getStringTime
import com.mentarey.easyweather.utils.SystemInfoUtils.nowTimeMillis

data class WeatherNow(
    val city: String = "Брест",
    val currentTime: String = getStringTime(nowTimeMillis),
    val temperature: Temperature = Temperature(),
    val currentWeather: CurrentWeather = CurrentWeather(),
    val dayDuration: DayDuration = DayDuration(),
    val wind: Wind = Wind(),
    val extraOptions: ExtraOptions = ExtraOptions()
)

/**
 * Main
 */

data class CurrentWeather(
    val forecast: String = "Clouds",                // текущий прогноз
    val forecastWithLocale: String = "облачно"     // текущий прогноз на языке системы
)

fun CurrentWeather.getIconResId(): Int = when (forecast) {
    "Clouds" -> R.drawable.icon_cloud
    "Rain" -> R.drawable.icon_rain
    "Sun" -> R.drawable.icon_sun
    "Snow" -> R.drawable.icon_snow
    "Clear" -> R.drawable.icon_clear
    else -> R.drawable.icon_clear
}

fun CurrentWeather.getBackgroundResId(): Int = when (forecast) {
    "Clouds" -> R.drawable.bg_clouds
    "Rain" -> R.drawable.bg_rain
    "Sun" -> R.drawable.bg_sun
    "Snow" -> R.drawable.bg_snow
    "Clear" -> R.drawable.bg_clear
    else -> R.drawable.bg_default
}

data class DayDuration(
    val sunrise: Long = 1579775762,
    val sunset: Long = 1579797733
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

data class Wind(
    val speed: Double = 10.0,       // скорость вертра
    val gust: Double = 15.0,        // максимальные порывы
    val degree: Int = 0            // направление (o)
)

data class ExtraOptions(
    val cloudiness: Int = 90,        // процент покрытия облаками - чем выше, чем больше облаков
    val pressure: Int = 760,    // давление в мм. рт. ст.
    val humidity: Int = 80,
    val visibility: Double = 5.0
)