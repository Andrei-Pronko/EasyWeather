package com.mentarey.easyweather.ui.widget

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.isSystemInDarkTheme
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.preferredHeight
import androidx.ui.material.ColorPalette
import androidx.ui.material.MaterialTheme
import androidx.ui.material.darkColorPalette
import androidx.ui.material.lightColorPalette
import androidx.ui.unit.dp

@Composable
fun Scaffold(
    appThemeSupport: AppThemeSupport = AppThemeSupport.FollowSystem,
    userLightColorPalette: ColorPalette = lightColorPalette(),
    userDarkColorPalette: ColorPalette = darkColorPalette(),
    appBar: @Composable() () -> Unit,
    content: @Composable() () -> Unit
) {
    val currentThemePalette = appThemeSupport.getCurrentColorPalette(
        lightColorPalette = userLightColorPalette,
        darkColorPalette = userDarkColorPalette
    )

    MaterialTheme(currentThemePalette) {
        Column {
            Box(Modifier.preferredHeight(56.dp) + Modifier.fillMaxWidth()) {
                appBar()
            }
            Box(Modifier.weight(1f)) {
                content()
            }
        }
    }
}

sealed class AppThemeSupport {
    object OnlyLight : AppThemeSupport()
    object OnlyDark : AppThemeSupport()
    object FollowSystem : AppThemeSupport()
}

@Composable
private fun AppThemeSupport.getCurrentColorPalette(
    lightColorPalette: ColorPalette = lightColorPalette(),
    darkColorPalette: ColorPalette = darkColorPalette()
): ColorPalette = when (this) {
    is AppThemeSupport.OnlyLight -> lightColorPalette
    is AppThemeSupport.OnlyDark -> darkColorPalette
    is AppThemeSupport.FollowSystem -> when (isSystemInDarkTheme()) {
        true -> darkColorPalette
        false -> lightColorPalette
    }
}