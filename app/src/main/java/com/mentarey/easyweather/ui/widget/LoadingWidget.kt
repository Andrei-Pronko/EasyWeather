package com.mentarey.easyweather.ui.widget

import androidx.compose.Composable
import androidx.ui.layout.Container
import androidx.ui.layout.EdgeInsets
import androidx.ui.material.LinearProgressIndicator
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp

@Composable
fun WeatherLoading() {
    val progressColor = MaterialTheme.colors().primary
    Container(padding = EdgeInsets(16.dp)) {
        LinearProgressIndicator(progressColor)
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