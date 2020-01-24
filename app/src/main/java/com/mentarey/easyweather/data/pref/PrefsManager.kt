package com.mentarey.easyweather.data.pref

interface PrefsManager {
    suspend fun getBooleanValue(key: String, defaultValue: Boolean): Boolean
    suspend fun getStringValue(key: String, defaultValue: String): String
    suspend fun putBooleanValueByKey(key: String, value: Boolean)
    suspend fun putStringValueByKey(key: String, value: String)
}