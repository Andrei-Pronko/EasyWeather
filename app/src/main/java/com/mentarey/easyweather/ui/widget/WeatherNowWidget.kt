package com.mentarey.easyweather.ui.widget

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.core.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.graphics.Color
import androidx.ui.graphics.Shadow
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontFamily
import androidx.ui.unit.dp
import androidx.ui.unit.px
import com.mentarey.easyweather.data.weather.model.current.*
import com.mentarey.easyweather.ui.button.VectorImage

@Composable
fun WeatherNowWidget(now: WeatherNow) {
    Column(LayoutPadding(8.dp)) {
        WeatherCityWidget(now.city)
        WeatherTimeWidget(now.currentTime)
        WeatherTemperatureWidget(now.temperature)
        Spacer(LayoutHeight(height = 24.dp))
        CurrentWeatherWidget(now.currentWeather)
        Spacer(LayoutHeight(height = 12.dp))
        DayDurationWidget(now.dayDuration)
        Spacer(LayoutHeight(height = 12.dp))
        WindWidget(now.wind)
        Spacer(LayoutHeight(height = 12.dp))
        ExtraOptionsWidget(now.extraOptions)
    }
}

@Composable
fun WeatherCityWidget(city: String) {
    val typography = MaterialTheme.typography()
    CustomizableWeatherText(city, LayoutHeight(80.dp), typography.h3)
}

@Composable
fun WeatherTimeWidget(currentTime: String) {
    val typography = MaterialTheme.typography()
    CustomizableWeatherText(currentTime, LayoutHeight(45.dp), typography.h5)
}

@Composable
fun WeatherTemperatureWidget(temperature: Temperature) {
    val currentTemp = "${temperature.current}$DEGREE_SYMBOL"
    val feelsLikeTemp = "Чувствуется как ${temperature.feelsLike}$DEGREE_SYMBOL"
    val typography = MaterialTheme.typography()
    Column {
        CustomizableWeatherText(currentTemp, LayoutHeight(100.dp), typography.h1)
        Spacer(LayoutHeight(height = 16.dp))
        CustomizableWeatherText(feelsLikeTemp, LayoutHeight(30.dp), typography.h5)
    }
}

@Composable
fun CurrentWeatherWidget(currentWeather: CurrentWeather) {
    val weatherIconId = currentWeather.getIconResId()
    val typography = MaterialTheme.typography()
    Row(LayoutWidth.Fill + LayoutHeight(40.dp)) {
        Padding(padding = 8.dp) {
            VectorImage(id = weatherIconId)
        }
        CustomizableWeatherText(currentWeather.forecastWithLocale, Modifier.None, typography.h5)
    }
}

@Composable
fun DayDurationWidget(dayDuration: DayDuration) {
    val typography = MaterialTheme.typography()
    val dayLength = (dayDuration.sunset - dayDuration.sunrise) / 60
    val dayLengthMin = (dayLength % 60).toInt()
    val dayLengthHours = (dayLength / 60).toInt()
    CustomizableWeatherText(
        "Продолжительность дня: ${dayLengthHours}ч. ${dayLengthMin}м.",
        Modifier.None,
        typography.h6
    )
}

@Composable
fun WindWidget(wind: Wind) {
    val typography = MaterialTheme.typography()
    Column {
        CustomizableWeatherText("Скорость ветра: ${wind.speed} м/c.", Modifier.None, typography.h6)
        if (wind.gust > 0.0)
            CustomizableWeatherText("Порывы до: ${wind.gust} м/c.", Modifier.None, typography.h6)
        CustomizableWeatherText("Направление: ${wind.degree}°", Modifier.None, typography.h6)
    }
}

@Composable
fun ExtraOptionsWidget(extraOptions: ExtraOptions) {
    val typography = MaterialTheme.typography()
    Column {
        CustomizableWeatherText(
            "Видимость: ${extraOptions.visibility} км.",
            Modifier.None,
            typography.h6
        )
        CustomizableWeatherText(
            "Облачность: ${extraOptions.cloudiness}%",
            Modifier.None,
            typography.h6
        )
        CustomizableWeatherText(
            "Влажность: ${extraOptions.humidity}%",
            Modifier.None,
            typography.h6
        )
        CustomizableWeatherText(
            "Давление: ${extraOptions.pressure} мм.рт.ст",
            Modifier.None,
            typography.h6
        )
    }
}

/**
 * Previews
 */

@Composable
fun CustomizableWeatherText(text: String, modifier: Modifier, textStyle: TextStyle) {
    Text(
        modifier = modifier,
        text = text,
        style = TextStyle(
            color = MaterialTheme.colors().onPrimary,
            fontFamily = FontFamily.SansSerif,
            fontWeight = textStyle.fontWeight,
            fontSize = textStyle.fontSize,
            shadow = Shadow(color = Color.Black, blurRadius = 2.px)
        )
    )
}

const val DEGREE_SYMBOL = "°C"