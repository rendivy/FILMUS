package com.example.cinema_app.presentation.usecases

import com.example.cinema_app.presentation.ValidationResult
import com.example.cinema_app.presentation.common.ErrorConstant

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