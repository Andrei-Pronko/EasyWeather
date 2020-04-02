package com.mentarey.easyweather.ui.widget

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.layout.wrapContentSize
import androidx.ui.material.LinearProgressIndicator
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp

@Composable
fun WeatherLoading() {
    val progressColor = MaterialTheme.colors.primary
    Box(padding = 16.dp) {
        LinearProgressIndicator(Modifier.wrapContentSize(Alignment.TopCenter), progressColor)
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