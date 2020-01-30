package com.mentarey.easyweather.ui.widget

import androidx.compose.Composable
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutHeight
import androidx.ui.material.MaterialTheme
import androidx.ui.unit.dp

@Composable
fun Scaffold(appBar: @Composable() () -> Unit, content: @Composable() () -> Unit) {
    MaterialTheme {
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