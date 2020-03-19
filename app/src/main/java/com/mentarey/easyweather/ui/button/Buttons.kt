package com.mentarey.easyweather.ui.button

import androidx.annotation.DrawableRes
import androidx.compose.Composable
import androidx.ui.foundation.Box
import androidx.ui.foundation.Clickable
import androidx.ui.graphics.Color
import androidx.ui.graphics.vector.drawVector
import androidx.ui.layout.LayoutSize
import androidx.ui.material.ripple.Ripple
import androidx.ui.res.vectorResource

@Composable
fun VectorImageButton(@DrawableRes id: Int, onClick: () -> Unit) {
    Ripple(bounded = false) {
        Clickable(onClick = onClick) {
            VectorImage(id = id)
        }
    }
}

@Composable
fun VectorImage(
        @DrawableRes id: Int,
        tint: Color = Color.Transparent
) {
    val vector = vectorResource(id)
    Box(modifier = LayoutSize(vector.defaultWidth, vector.defaultHeight) + drawVector(vector, tint))
}