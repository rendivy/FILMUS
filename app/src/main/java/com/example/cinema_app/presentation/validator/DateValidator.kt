package com.example.cinema_app.presentation.validator

import com.example.cinema_app.common.ErrorConstant

class DateValidator {
    fun execute(date: String): ValidationResult {
        return if (date.isEmpty()) {
            ValidationResult(
                successful = false,
                errorMessage = ErrorConstant.DATE_ERROR
            )
        } else {
            ValidationResult(
                successful = true,
            )
        }
    }
}