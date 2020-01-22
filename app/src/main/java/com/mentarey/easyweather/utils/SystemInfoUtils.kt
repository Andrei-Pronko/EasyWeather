package com.mentarey.easyweather.utils

import java.text.SimpleDateFormat
import java.util.*

object SystemInfoUtils {

    private const val summaryDateFormatString = "dd.MM.yyyy HH:mm:ss"
    private const val fileNameDateFormatString = "yyyyMMdd_HH-mm-ss-SSS_"

    val currentLanguage: String
        get() = Locale.getDefault().language

    val fileCreationDate: String
        get() = fileNameDateFormat.format(Calendar.getInstance().time)

    val nowTimeMillis: Long
        get() = Calendar.getInstance().time.time

    val lastMonthTimeMillis: Long
        get() = Calendar.getInstance().apply { add(Calendar.MONTH, -1) }.timeInMillis

    val formattedSummaryDate: String
        get() = summaryDateFormat.format(Calendar.getInstance().time)

    fun getStringTime(time: Long): String {
        return summaryDateFormat.format(time)
    }

    fun getStringTimeFromStringData(data: String): String {
        return summaryDateFormat.format(Date(java.lang.Long.valueOf(data)))
    }

    private val summaryDateFormat: SimpleDateFormat
        get() = SimpleDateFormat(summaryDateFormatString, Locale.US)

    private val fileNameDateFormat: SimpleDateFormat
        get() = SimpleDateFormat(fileNameDateFormatString, Locale.US)
}