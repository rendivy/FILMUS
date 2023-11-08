package com.example.cinema_app.data.converter

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


class DateConverter {
    fun convertMillisToDateString(millis: Long, locale: Locale = Locale.getDefault()): String {
        val sdf = SimpleDateFormat("yyyy.MM.dd", locale)
        sdf.timeZone =
            TimeZone.getTimeZone("UTC")
        val date = Date(millis)
        return sdf.format(date)
    }

    fun convertUiDateToRemote(date: String): String {
        val inputFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        val parsedDate = inputFormat.parse(date)

        return parsedDate?.let { outputFormat.format(it) } ?: date
    }

    fun convertDateToUi(date: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val parsedDate = inputFormat.parse(date)
        return parsedDate?.let { outputFormat.format(it) } ?: ""

    }
}
