package com.mentarey.easyweather.ui.widget

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Modifier
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.px
import androidx.ui.graphics.Color
import androidx.ui.graphics.Shadow
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontFamily
import androidx.ui.tooling.preview.Preview
import com.mentarey.easyweather.data.weather.model.current.CurrentWeather
import com.mentarey.easyweather.data.weather.model.current.Temperature
import com.mentarey.easyweather.data.weather.model.current.WeatherNow
import com.mentarey.easyweather.R
import com.mentarey.easyweather.ui.button.VectorImage

@Composable
fun WeatherNowWidget(now: WeatherNow) {
    Padding(padding = 8.dp) {
        Column {
            WeatherCityWidget(now.city)
            WeatherTimeWidget(now.currentTime, now.lastWeatherUpdate)
            WeatherTemperatureWidget(now.temperature)
            HeightSpacer(height = 24.dp)
            CurrentWeatherWidget(now.currentWeather)
        }
    }
}

@Composable
fun WeatherCityWidget(city: String) {
    val cityTextStyle = (+MaterialTheme.typography()).h3
    CustomizableWeatherText(city, Height(80.dp) wraps ExpandedWidth, cityTextStyle)
}

@Composable
fun WeatherTimeWidget(currentTime: String, lastTimeUpdate: String) {
    val typography = +MaterialTheme.typography()
    val lastUpdate = "Обновлено: $lastTimeUpdate"
    Column {
        CustomizableWeatherText(currentTime, Height(45.dp) wraps ExpandedWidth, typography.h5)
        HeightSpacer(height = 4.dp)
        CustomizableWeatherText(lastUpdate, Height(30.dp) wraps ExpandedWidth, typography.subtitle2)
    }
}

@Composable
fun WeatherTemperatureWidget(temperature: Temperature) {
    val currentTemp = "${temperature.current}$DEGREE_SYMBOL"
    val feelsLikeTemp = "Чувствуется как ${temperature.feelsLike}$DEGREE_SYMBOL"
    val typography = (+MaterialTheme.typography())
    Column {
        CustomizableWeatherText(currentTemp, Height(100.dp) wraps ExpandedWidth, typography.h1)
        HeightSpacer(height = 16.dp)
        CustomizableWeatherText(feelsLikeTemp, Height(30.dp) wraps ExpandedWidth, typography.h5)
    }
}

@Composable
fun CurrentWeatherWidget(currentWeather: CurrentWeather) {
    val weatherIconId = when (currentWeather.forecast) {
        "Clouds" -> R.drawable.baseline_wb_cloudy_24
        else -> R.drawable.baseline_wb_sunny_24
    }
    val typography = (+MaterialTheme.typography())
    Row(ExpandedWidth wraps Height(40.dp)) {
        Padding(padding = 8.dp) {
            VectorImage(id = weatherIconId)
        }
        CustomizableWeatherText(currentWeather.forecastWithLocale, Modifier.None, typography.h4)
    }
}

/**
 * Previews
 */

@Preview
@Composable
fun WeatherNowWidgetPreview() {
    WeatherNowWidget(WeatherNow())
}

@Composable
private fun CustomizableWeatherText(text: String, modifier: Modifier, textStyle: TextStyle) {
    Text(
        modifier = modifier,
        text = text,
        style = TextStyle(
            color = Color.White,
            fontFamily = FontFamily.Monospace,
            fontWeight = textStyle.fontWeight,
            fontSize = textStyle.fontSize,
            shadow = Shadow(color = Color.Black, blurRadius = 2.px)
        )
    )
}

const val DEGREE_SYMBOL = "°C"