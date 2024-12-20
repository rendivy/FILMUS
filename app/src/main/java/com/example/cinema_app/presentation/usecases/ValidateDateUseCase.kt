package com.example.cinema_app.presentation.usecases

import com.example.cinema_app.common.ErrorConstant
import com.example.cinema_app.presentation.ValidationResult

class ValidateDateUseCase {

    operator fun invoke(date: String): ValidationResult {
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