package com.mentarey.easyweather.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class EasyWeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@EasyWeatherApp)
            modules(listOf(prefModule, retrofitModule, uiModule))
        }
    }
}