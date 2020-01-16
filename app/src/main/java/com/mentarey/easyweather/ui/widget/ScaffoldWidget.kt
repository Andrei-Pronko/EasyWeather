package com.mentarey.easyweather.ui.widget

import androidx.compose.Composable
import androidx.ui.layout.FlexColumn
import androidx.ui.material.MaterialTheme

@Composable
fun Scaffold(appBar: @Composable() () -> Unit, content: @Composable() () -> Unit) {
    MaterialTheme {
        FlexColumn {
            inflexible {
                appBar()
            }
            expanded(flex = 1F) {
                content()
            }
        }
    }
}