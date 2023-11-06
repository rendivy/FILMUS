package com.example.cinema_app.domain.usecase

import com.example.cinema_app.data.converter.DateConverter
import javax.inject.Inject

class ConvertDateUseCase @Inject constructor(private val dateConverter: DateConverter){

        fun execute(date: String): String {
            return dateConverter.convertDateToUi(date)
        }
}