package com.example.cinema_app.presentation.usecases

import com.example.cinema_app.common.ErrorConstant
import com.example.cinema_app.presentation.ValidationResult

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