package com.example.cinema_app.domain.usecase

import com.example.cinema_app.common.ErrorConstant
import com.example.cinema_app.presentation.validator.ValidationResult

class ValidateCofirmPasswordUseCase {
    fun execute(password: String, confirmPassword: String): ValidationResult {
        return if (password != confirmPassword) {
            ValidationResult(
                successful = false,
                errorMessage = ErrorConstant.CONFIRMATION_ERROR
            )
        } else {
            ValidationResult(
                successful = true,
            )
        }
    }
}