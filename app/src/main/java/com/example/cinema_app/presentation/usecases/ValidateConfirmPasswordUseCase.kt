package com.example.cinema_app.presentation.usecases

import com.example.cinema_app.presentation.ValidationResult
import com.example.cinema_app.presentation.common.ErrorConstant

class ValidateConfirmPasswordUseCase {

    operator fun invoke(password: String, confirmPassword: String): ValidationResult {
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