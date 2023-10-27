package com.example.cinema_app.data.converter

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


class DateConverter {
    fun convertMillisToDateString(millis: Long, locale: Locale = Locale.getDefault()): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", locale)
        sdf.timeZone = TimeZone.getTimeZone("UTC") // Установите нужный часовой пояс здесь, если он отличается от UTC
        val date = Date(millis)
        return sdf.format(date)
    }
}
