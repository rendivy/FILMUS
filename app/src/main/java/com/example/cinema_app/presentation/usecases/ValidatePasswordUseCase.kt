package com.example.cinema_app.presentation.usecases

import com.example.cinema_app.presentation.ValidationResult
import com.example.cinema_app.presentation.common.ErrorConstant

class ValidatePasswordUseCase {

    operator fun invoke(password: String): ValidationResult {
        return if (password.length < 6) {
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


}