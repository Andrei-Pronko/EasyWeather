package com.mentarey.easyweather.data.weather

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherClient {

    val weatherApi: WeatherApi by lazy { retrofit.create(WeatherApi::class.java) }

    private val httpLoggingInterceptor: HttpLoggingInterceptor
        get() = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    private val withHeadersInterceptor: Interceptor
        get() = Interceptor { chain: Interceptor.Chain ->
            val request = chain.request().newBuilder()
                .addHeader(
                    "x-rapidapi-host",
                    "community-open-weather-map.p.rapidapi.com"
                )
                .addHeader(
                    "x-rapidapi-key",
                    "3954789a2dmshd45d21e798f8e93p1cf0c6jsna49b34e49374"
                )
                .build()
            chain.proceed(request)
        }

    private val okHttpClient: OkHttpClient
        get() = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(withHeadersInterceptor)
            .build()

    private val gsonConverterFactory: GsonConverterFactory
        get() {
            val gson = GsonBuilder()
                .setLenient()
                .create()
            return GsonConverterFactory.create(gson)
        }

    private val coroutineCallAdapterFactory: CallAdapter.Factory
        get() = CoroutineCallAdapterFactory()

    private val retrofit: Retrofit
        get() {
            return Retrofit.Builder()
                .baseUrl(BASE_WEATHER_API_URL)
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(coroutineCallAdapterFactory)
                .build()
        }

    private const val BASE_WEATHER_API_URL = "https://community-open-weather-map.p.rapidapi.com/"
}