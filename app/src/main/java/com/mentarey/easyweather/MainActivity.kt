package com.mentarey.easyweather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.state
import androidx.lifecycle.LiveData
import androidx.ui.core.setContent
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
        val localWeatherState by state { EasyWeatherScreenState() }

        var lastCityState by state { easyWeatherViewModel.lastCity.current }

        localWeatherState.lastCity = observe(easyWeatherViewModel.lastCity)
        localWeatherState.loadingState = observe(easyWeatherViewModel.loading)
        localWeatherState.weatherNow = observe(easyWeatherViewModel.weatherNow)

        val onWeatherCityChanged: (String) -> Unit =
            { easyWeatherViewModel.getWeatherInTheCity(it) }
        val onSearchButtonClick: () -> Unit =
            { easyWeatherViewModel.getWeatherInTheCity(lastCityState) }
        val onTextEnterStopped: (String) -> Unit = {
            lastCityState = it
            easyWeatherViewModel.saveLastCity(it)
        }

        Scaffold(
            appBar = {
                EasyWeatherAppBar(
                    defaultCity = localWeatherState.lastCity,
                    onSearchButtonClick = onSearchButtonClick,
                    onSearchButtonClicked = onWeatherCityChanged,
                    onTextEnterStopped = onTextEnterStopped
                )
            },
            content = {
                EasyWeatherContent(easyWeatherScreenState = localWeatherState)
            }
        )
    }

    @Preview
    @Composable
    fun DefaultPreview() {
        EasyWeatherApp()
    }

    private val LiveData<String>.current: String
        get() = value ?: ""
}