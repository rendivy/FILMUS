package com.example.cinema_app.presentation.usecases

import com.example.cinema_app.presentation.ValidationResult
import com.example.cinema_app.presentation.common.ErrorConstant

class ValidatePasswordUseCase {

    operator fun invoke(password: String): ValidationResult {
        return if (password.length < MIN_PASSWORD_LENGTH) {
            ValidationResult(
                successful = false,
                errorMessage = ErrorConstant.PASSWORD_LENGTH_ERROR
            )
        } else {
            ValidationResult(
                successful = true,
            )
        }
    }

    private companion object {
        const val MIN_PASSWORD_LENGTH = 8
    }
}