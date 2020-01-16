package com.mentarey.easyweather.ui.widget

import androidx.compose.Composable
import androidx.compose.ambient
import androidx.compose.unaryPlus
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Text
import androidx.ui.layout.Column
import androidx.ui.material.TopAppBar
import com.mentarey.easyweather.EasyWeatherState
import com.mentarey.easyweather.R
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
    Column {
        LoadingWidget(state.loadingState, retryWeatherLoading, updateWeather)
    }
}