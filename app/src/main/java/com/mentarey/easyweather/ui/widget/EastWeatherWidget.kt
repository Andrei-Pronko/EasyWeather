package com.mentarey.easyweather.ui.widget

import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.ContextAmbient
import androidx.ui.core.TextField
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Image
import androidx.ui.graphics.Color
import androidx.ui.graphics.ScaleFit
import androidx.ui.graphics.imageFromResource
import androidx.ui.input.ImeAction
import androidx.ui.input.KeyboardType
import androidx.ui.layout.*
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.material.IconButton
import androidx.ui.material.TopAppBar
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.Search
import androidx.ui.text.TextFieldValue
import androidx.ui.tooling.preview.Preview
import com.mentarey.easyweather.data.weather.model.current.CurrentWeather
import com.mentarey.easyweather.data.weather.model.current.getBackgroundResId
import com.mentarey.easyweather.ui.state.EasyWeatherScreenState
import com.mentarey.easyweather.ui.state.WeatherLoadingState
import com.mentarey.easyweather.utils.hideKeyboard

@Composable
fun EasyWeatherAppBar(
    defaultCity: String,
    onSearchButtonClick: () -> Unit,
    onSearchButtonClicked: (String) -> Unit,
    onTextEnterStopped: (String) -> Unit
) {
    TopAppBar(
        title = {
            EnterCityWidget(
                defaultCity = defaultCity,
                onSearchButtonClicked = onSearchButtonClicked,
                onTextEnterStopped = onTextEnterStopped
            )
        },
        actions = {
            IconButton(onClick = onSearchButtonClick) {
                Icon(icon = Icons.Filled.Search)
            }
        }
    )
}

@Composable
fun EnterCityWidget(
    defaultCity: String,
    onSearchButtonClicked: (String) -> Unit,
    onTextEnterStopped: (String) -> Unit
) {
    val context = ContextAmbient.current
    var state by state { TextFieldValue(defaultCity) }
    Center {
        TextField(
            value = state,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search,
            onValueChange = { state = it },
            onImeActionPerformed = {
                if (it == ImeAction.Search) {
                    onSearchButtonClicked(state.text)
                    onTextEnterStopped(state.text)
                    context.hideKeyboard()
                }
            }
        )
    }
}

@Composable
fun EasyWeatherContent(easyWeatherScreenState: EasyWeatherScreenState) {
    Stack {
        EasyWeatherBackground(easyWeatherScreenState.weatherNow.currentWeather)
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
        CircularProgressIndicator(Color.Blue)
    }
}

@Composable
fun EasyWeatherBackground(currentWeather: CurrentWeather) {
    val context = ContextAmbient.current
    val image = imageFromResource(context.resources, currentWeather.getBackgroundResId())
    Image(image = image, modifier = LayoutSize.Fill, scaleFit = ScaleFit.FillHeight)
}

@Preview
@Composable
fun EasyWeatherAppBarPreview() {
    EasyWeatherAppBar("Brest", {}, {}, {})
}