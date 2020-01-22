package com.mentarey.easyweather.data.weather

import com.mentarey.easyweather.data.weather.model.WeatherInfo
import com.mentarey.easyweather.utils.SystemInfoUtils
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/weather")
    fun getCurrentWeatherAsync(
            @Query("q") q: String,
            @Query("lat") lat: String,
            @Query("lon") lon: String,
            @Query("units") units: String = DEFAULT_METRIC_SYSTEM,
            @Query("lang") lang: String = SystemInfoUtils.currentLanguage
    ): Deferred<WeatherInfo>
}

const val DEFAULT_METRIC_SYSTEM = "metric"