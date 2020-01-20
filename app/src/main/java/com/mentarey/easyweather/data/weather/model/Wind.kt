package com.mentarey.easyweather.data.weather.model

import com.google.gson.annotations.SerializedName

data class Wind(
    @SerializedName("speed")
    val speed: Double
)