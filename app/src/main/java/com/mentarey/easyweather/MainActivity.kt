package com.mentarey.easyweather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.Model
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import com.mentarey.easyweather.ui.widget.Scaffold
import com.mentarey.easyweather.ui.model.WeatherLoadingState
import com.mentarey.easyweather.ui.model.WeatherType
import com.mentarey.easyweather.ui.widget.EasyWeatherAppBar
import com.mentarey.easyweather.ui.widget.EasyWeatherContent
import com.mentarey.easyweather.utils.observe

@Model
data class EasyWeatherState(
    var loadingState: WeatherLoadingState = WeatherLoadingState.Success,
    var weatherType: WeatherType = WeatherType.Sun
)

class MainActivity : AppCompatActivity() {

    private val easyWeatherViewModel: EasyWeatherViewModel = EasyWeatherViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EasyWeatherApp()
        }
    }

    @Composable
    fun EasyWeatherApp() {
        val localWeatherState by +state { EasyWeatherState() }

        // Наблюдаем за изменением состояния
        localWeatherState.loadingState = +observe(easyWeatherViewModel.loading)
        localWeatherState.weatherType = +observe(easyWeatherViewModel.weatherType)

        val onNavigationButtonClick: () -> Unit = {}
        Scaffold(
            appBar = {
                EasyWeatherAppBar(onNavigationButtonClick)
            },
            content = {
                EasyWeatherContent(
                    state = localWeatherState,
                    retryWeatherLoading = { easyWeatherViewModel.retryWeatherLoading() },
                    updateWeather = { easyWeatherViewModel.updateWeather() })
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