package com.mentarey.easyweather.utils

import java.text.SimpleDateFormat
import java.util.*

object SystemInfoUtils {

    private const val summaryDateFormatString = "dd.MM.yyyy HH:mm:ss"
    private const val fileNameDateFormatString = "yyyyMMdd_HH-mm-ss-SSS_"

    val currentLanguage: String
        get() = Locale.getDefault().language

    val nowTimeMillis: Long
        get() = Calendar.getInstance().time.time

    fun getStringTime(time: Long): String {
        return summaryDateFormat.format(time)
    }

    private val summaryDateFormat: SimpleDateFormat
        get() = SimpleDateFormat(summaryDateFormatString, Locale.US)

    private val fileNameDateFormat: SimpleDateFormat
        get() = SimpleDateFormat(fileNameDateFormatString, Locale.US)
}