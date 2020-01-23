package com.mentarey.easyweather.ui.widget

import androidx.compose.Composable
import androidx.compose.ambient
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.core.*
import androidx.ui.foundation.SimpleImage
import androidx.ui.graphics.imageFromResource
import androidx.ui.input.ImeAction
import androidx.ui.input.KeyboardType
import androidx.ui.layout.*
import androidx.ui.material.TopAppBar
import com.mentarey.easyweather.EasyWeatherScreenState
import com.mentarey.easyweather.R
import com.mentarey.easyweather.data.weather.model.current.CurrentWeather
import com.mentarey.easyweather.data.weather.model.current.getBackgroundResId
import com.mentarey.easyweather.ui.button.VectorImageButton
import com.mentarey.easyweather.ui.model.WeatherLoadingState
import com.mentarey.easyweather.utils.hideKeyboard

@Composable
fun EasyWeatherAppBar(
    onSearchButtonClick: () -> Unit,
    onWeatherCityChanged: (String) -> Unit
) {
    TopAppBar(
        title = {
            EnterCityWidget(onWeatherCityChanged = onWeatherCityChanged)
        },
        navigationIcon = {
            VectorImageButton(id = R.drawable.baseline_search_24) { onSearchButtonClick() }
        }
    )
}

@Composable
fun EnterCityWidget(onWeatherCityChanged: (String) -> Unit) {
    val context = +ambient(ContextAmbient)
    var state by +state { EditorModel(context.getString(R.string.default_weather_city)) }
    Center {
        TextField(
            value = state,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search,
            onValueChange = { state = it },
            onImeActionPerformed = {
                if (it == ImeAction.Search) {
                    onWeatherCityChanged(state.text)
                    context.hideKeyboard()
                }
            }
        )
    }
}

@Composable
fun EasyWeatherContent(easyWeatherScreenState: EasyWeatherScreenState) {
    Stack {
        expanded {
            EasyWeatherBackground(easyWeatherScreenState.weatherNow.currentWeather)
        }
        aligned(Alignment.TopCenter) {
            when (easyWeatherScreenState.loadingState) {
                WeatherLoadingState.Loading -> WeatherLoading()
                else -> WeatherNowWidget(easyWeatherScreenState.weatherNow)
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