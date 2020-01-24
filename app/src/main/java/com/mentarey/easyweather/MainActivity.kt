package com.mentarey.easyweather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.lifecycle.lifecycleScope
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import com.mentarey.easyweather.ui.state.EasyWeatherScreenState
import com.mentarey.easyweather.ui.widget.EasyWeatherAppBar
import com.mentarey.easyweather.ui.widget.EasyWeatherContent
import com.mentarey.easyweather.ui.widget.Scaffold
import com.mentarey.easyweather.utils.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val easyWeatherViewModel: EasyWeatherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EasyWeatherApp()
        }
    }

    @Composable
    fun EasyWeatherApp() {
        val localWeatherState by +state { EasyWeatherScreenState() }

        localWeatherState.lastCity = +observe(easyWeatherViewModel.lastCity)
        localWeatherState.loadingState = +observe(easyWeatherViewModel.loading)
        localWeatherState.weatherNow = +observe(easyWeatherViewModel.weatherNow)

        val onWeatherCityChanged: (String) -> Unit =
            { easyWeatherViewModel.getWeatherInTheCity(it) }
        val onSearchButtonClick: () -> Unit =
            { easyWeatherViewModel.getWeatherInTheCity(localWeatherState.weatherNow.city) }

        Scaffold(
            appBar = {
                EasyWeatherAppBar(
                    defaultCity = localWeatherState.lastCity,
                    onSearchButtonClick = onSearchButtonClick,
                    onWeatherCityChanged = onWeatherCityChanged
                )
            },
            content = {
                EasyWeatherContent(
                    easyWeatherScreenState = localWeatherState
                )
            }
        )
    }

    @Preview
    @Composable
    fun DefaultPreview() {
        MaterialTheme {
            EasyWeatherApp()
        }
    }
}