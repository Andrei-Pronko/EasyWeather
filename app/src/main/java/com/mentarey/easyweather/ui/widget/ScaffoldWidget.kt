package com.mentarey.easyweather.ui.widget

import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.foundation.isSystemInDarkTheme
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutHeight
import androidx.ui.material.MaterialTheme
import androidx.ui.material.darkColorPalette
import androidx.ui.material.lightColorPalette
import androidx.ui.unit.dp

@Composable
fun Scaffold(appBar: @Composable() () -> Unit, content: @Composable() () -> Unit) {
    val currentThemePalette = when (isSystemInDarkTheme()) {
        true -> darkColorPalette()
        false -> lightColorPalette()
    }
    MaterialTheme(currentThemePalette) {
        Column {
            Container(LayoutHeight(56.dp)) {
                appBar()
            }
            Container(LayoutFlexible(1f)) {
                content()
            }
        }
    }
}