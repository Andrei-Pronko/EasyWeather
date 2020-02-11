package com.mentarey.easyweather.ui.widget

import androidx.compose.Composable
import androidx.compose.ambient
import androidx.compose.state
import androidx.ui.core.ContextAmbient
import androidx.ui.core.EditorModel
import androidx.ui.core.TextField
import androidx.ui.foundation.SimpleImage
import androidx.ui.graphics.imageFromResource
import androidx.ui.input.ImeAction
import androidx.ui.input.KeyboardType
import androidx.ui.layout.*
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.material.TopAppBar
import androidx.ui.tooling.preview.Preview
import com.mentarey.easyweather.R
import com.mentarey.easyweather.data.weather.model.current.CurrentWeather
import com.mentarey.easyweather.data.weather.model.current.getBackgroundResId
import com.mentarey.easyweather.ui.button.VectorImageButton
import com.mentarey.easyweather.ui.state.EasyWeatherScreenState
import com.mentarey.easyweather.ui.state.WeatherLoadingState
import com.mentarey.easyweather.utils.hideKeyboard

@Composable
fun EasyWeatherAppBar(
    defaultCity: String,
    onSearchButtonClick: () -> Unit,
    onWeatherCityChanged: (String) -> Unit
) {
    TopAppBar(
        title = {
            EnterCityWidget(
                defaultCity = defaultCity,
                onWeatherCityChanged = onWeatherCityChanged
            )
        },
        navigationIcon = {
            VectorImageButton(
                id = R.drawable.baseline_loop_24
            ) {
                onSearchButtonClick()
            }
        }
    )
}

@Composable
fun EnterCityWidget(defaultCity: String, onWeatherCityChanged: (String) -> Unit) {
    val context = ambient(ContextAmbient)
    var state by state { EditorModel(defaultCity) }
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
        Container(LayoutSize.Fill) {
            EasyWeatherBackground(easyWeatherScreenState.weatherNow.currentWeather)
        }
        Container(LayoutGravity.TopCenter) {
            when (easyWeatherScreenState.loadingState) {
                WeatherLoadingState.Loading -> WeatherLoading()
                WeatherLoadingState.Empty -> EasyWeatherEmpty()
                else -> WeatherNowWidget(easyWeatherScreenState.weatherNow)
            }
        }
    }
}

@Composable
fun EasyWeatherEmpty() {
    Center {
        CircularProgressIndicator()
    }
}

@Composable
fun EasyWeatherBackground(currentWeather: CurrentWeather) {
    val context = ambient(ContextAmbient)
    val image = imageFromResource(context.resources, currentWeather.getBackgroundResId())
    Container {
        SimpleImage(image = image)
    }
}

@Preview
@Composable
fun EasyWeatherAppBarPreview() {
    EasyWeatherAppBar("Brest", {}, {})
}