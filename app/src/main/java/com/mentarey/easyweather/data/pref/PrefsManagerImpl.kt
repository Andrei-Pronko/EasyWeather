package com.mentarey.easyweather.data.pref

import android.content.SharedPreferences

class PrefsManagerImpl
constructor(private val preferences: SharedPreferences) : PrefsManager {

    override suspend fun getBooleanValue(key: String, defaultValue: Boolean): Boolean {
        return try {
            preferences.getBoolean(key, defaultValue)
        } catch (e: ClassCastException) {
            defaultValue
        }
    }

    override suspend fun getStringValue(key: String, defaultValue: String): String {
        return try {
            preferences.getString(key, defaultValue) ?: defaultValue
        } catch (e: ClassCastException) {
            defaultValue
        }
    }

    override suspend fun putBooleanValueByKey(key: String, value: Boolean) {
        preferences.edit().apply {
            putBoolean(key, value)
            apply()
        }
    }

    override suspend fun putStringValueByKey(key: String, value: String) {
        preferences.edit().apply {
            putString(key, value)
            apply()
        }
    }
}