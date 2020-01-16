package com.mentarey.easyweather.ui.widget

import androidx.compose.Composable
import androidx.compose.ambient
import androidx.compose.unaryPlus
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.LinearProgressIndicator
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import com.mentarey.easyweather.R
import com.mentarey.easyweather.ui.model.WeatherLoadingState

@Composable
fun LoadingWidget(
    weatherLoadingState: WeatherLoadingState,
    retryWeatherLoading: () -> Unit,
    updateWeather: () -> Unit
) {
    Container(modifier = Height(100.dp) wraps ExpandedWidth) {
        LoadingStateUi(weatherLoadingState, retryWeatherLoading, updateWeather)
    }
}

@Composable
fun LoadingStateUi(
    loadingState: WeatherLoadingState,
    retryWeatherLoading: () -> Unit,
    updateWeather: () -> Unit
) {
    when (loadingState) {
        is WeatherLoadingState.Loading -> WeatherLoading()
        is WeatherLoadingState.Error -> WeatherLoadingError(
            reason = loadingState.reason,
            retry = retryWeatherLoading
        )
        is WeatherLoadingState.Success -> WeatherLoadingSuccess(
            update = updateWeather
        )
    }
}

@Composable
fun WeatherLoading() {
    val progressColor = (+MaterialTheme.colors()).primary
    Padding(padding = 16.dp) {
        LinearProgressIndicator(progressColor)
    }
}

@Composable
fun WeatherLoadingError(reason: String, retry: () -> Unit = {}) {
    val context = +ambient(ContextAmbient)
    Padding(padding = 16.dp) {
        FlexColumn(
            mainAxisAlignment = MainAxisAlignment.SpaceAround,
            crossAxisAlignment = CrossAxisAlignment.Center
        ) {
            inflexible {
                Text(
                    text = reason,
                    style = (+MaterialTheme.typography()).subtitle1
                )
                HeightSpacer(height = 10.dp)
                Button(
                    text = context.getString(R.string.retry_weather_loading),
                    onClick = { retry() }
                )
            }
        }
    }
}

@Composable
fun WeatherLoadingSuccess(update: () -> Unit = {}) {
    val context = +ambient(ContextAmbient)
    Padding(padding = 16.dp) {
        Column(arrangement = Arrangement.Begin) {
            Button(
                text = context.getString(R.string.update_weather_info),
                onClick = { update() }
            )
        }
    }
}

/**
 * Previews
 */
@Preview
@Composable
fun WeatherLoadingPreview() {
    WeatherLoading()
}

@Preview
@Composable
fun WeatherLoadingErrorPreview() {
    WeatherLoadingError("Internet not available")
}

@Preview
@Composable
fun WeatherLoadingSuccessPreview() {
    WeatherLoadingSuccess()
}