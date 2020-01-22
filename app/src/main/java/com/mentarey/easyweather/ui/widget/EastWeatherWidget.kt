package com.mentarey.easyweather.ui.widget

import androidx.compose.Composable
import androidx.compose.ambient
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Text
import androidx.ui.foundation.SimpleImage
import androidx.ui.graphics.imageFromResource
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.Expanded
import androidx.ui.layout.Stack
import androidx.ui.material.TopAppBar
import com.mentarey.easyweather.EasyWeatherState
import com.mentarey.easyweather.R
import com.mentarey.easyweather.data.weather.model.current.CurrentWeather
import com.mentarey.easyweather.data.weather.model.current.getBackgroundResId
import com.mentarey.easyweather.ui.button.VectorImageButton

@Composable
fun EasyWeatherAppBar(onNavigationButtonClick: () -> Unit) {
    val context = +ambient(ContextAmbient)
    TopAppBar(title = { Text(text = context.getString(R.string.app_name)) }) {
        VectorImageButton(
            R.drawable.baseline_menu_24,
            onNavigationButtonClick
        )
    }
}

@Composable
fun EasyWeatherContent(
    state: EasyWeatherState,
    retryWeatherLoading: () -> Unit,
    updateWeather: () -> Unit
) {
    Stack {
        expanded {
            EasyWeatherBackground(state.weatherNow.currentWeather)
        }
        aligned(Alignment.TopCenter) {
            Column {
                LoadingWidget(state.loadingState, retryWeatherLoading, updateWeather)
                WeatherNowWidget(state.weatherNow)
            }
        }
    }
}

@Composable
fun EasyWeatherBackground(currentWeather: CurrentWeather) {
    val context = +ambient(ContextAmbient)
    val image = imageFromResource(context.resources, currentWeather.getBackgroundResId())
    Container(modifier = Expanded) {
        SimpleImage(image = image)
    }
}