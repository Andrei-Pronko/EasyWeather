package com.mentarey.easyweather.ui.state

import androidx.compose.Model
import com.mentarey.easyweather.data.weather.model.current.WeatherNow

@Model
data class EasyWeatherScreenState(
    var lastCity: String = "",
    var loadingState: WeatherLoadingState = WeatherLoadingState.Empty,
    var weatherNow: WeatherNow = WeatherNow()
)