package com.mentarey.easyweather.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mentarey.easyweather.EasyWeatherViewModel
import com.mentarey.easyweather.data.pref.LastWeatherRepository
import com.mentarey.easyweather.data.pref.LastWeatherRepositoryImpl
import com.mentarey.easyweather.data.pref.PrefsManager
import com.mentarey.easyweather.data.pref.PrefsManagerImpl
import com.mentarey.easyweather.data.weather.WeatherApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val prefModule = module {
    single { androidApplication().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE) }
    single<PrefsManager> { PrefsManagerImpl(get()) }
    single<LastWeatherRepository> { LastWeatherRepositoryImpl(get(), get()) }
}

val retrofitModule = module {
    single(named<HttpLoggingInterceptor>()) {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single(named<Interceptor>()) {
        Interceptor { chain: Interceptor.Chain ->
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
    }
    single {
        OkHttpClient.Builder()
            .addInterceptor(get(named<Interceptor>()))
            .addInterceptor(get(named<HttpLoggingInterceptor>()))
            .build()
    }
    single { GsonBuilder().setLenient().create() }
    single(named<Converter.Factory>()) { GsonConverterFactory.create(get()) }
    single(named<CallAdapter.Factory>()) { CoroutineCallAdapterFactory() }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_WEATHER_API_URL)
            .client(get())
            .addConverterFactory(get(named<Converter.Factory>()))
            .addCallAdapterFactory(get(named<CallAdapter.Factory>()))
            .build()
    }

    single<WeatherApi> { get<Retrofit>().create(WeatherApi::class.java) }
}

val uiModule = module {
    viewModel { EasyWeatherViewModel(get(), get(), get()) }
}

const val PREFERENCES_NAME = "com.mentarey.easyweather.weather_preferences"
const val BASE_WEATHER_API_URL = "https://community-open-weather-map.p.rapidapi.com/"