package com.mentarey.easyweather.data.weather

import com.mentarey.easyweather.data.weather.model.WeatherInfo
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface WeatherApi {
    @GET("/weather?lat=52.103001&lon=23.691125&lang=ru&units=metric&q=Brest")
    fun getWeatherInfoAsync(): Deferred<WeatherInfo>
}

data class Todo(
    val id: Int = 0,
    val title: String = "",
    val completed: Boolean = false
)