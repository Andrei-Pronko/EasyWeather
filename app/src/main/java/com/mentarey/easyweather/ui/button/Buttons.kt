package com.mentarey.easyweather.ui.button

import androidx.annotation.DrawableRes
import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.core.paint
import androidx.ui.foundation.Box
import androidx.ui.graphics.vector.VectorPainter
import androidx.ui.layout.preferredSize
import androidx.ui.res.vectorResource

@Composable
fun VectorImage(@DrawableRes id: Int) {
    val vector = vectorResource(id)
    Box(
        Modifier.preferredSize(vector.defaultWidth, vector.defaultHeight)
                + Modifier.paint(VectorPainter(vector))
    )
}