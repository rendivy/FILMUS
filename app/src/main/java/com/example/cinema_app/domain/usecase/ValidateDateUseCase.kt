package com.example.cinema_app.domain.usecase

import com.example.cinema_app.common.ErrorConstant
import com.example.cinema_app.presentation.validator.ValidationResult

class ValidateDateUseCase {
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