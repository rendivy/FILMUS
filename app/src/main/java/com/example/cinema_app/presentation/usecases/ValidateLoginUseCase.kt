package com.example.cinema_app.presentation.usecases

import com.example.cinema_app.common.ErrorConstant
import com.example.cinema_app.presentation.ValidationResult

class ValidateLoginUseCase {

    operator fun invoke(login: String, errorConstant: String = ErrorConstant.LOGIN_ERROR): ValidationResult {
        val letterCount = login.count { it.isLetter() }

        return if (letterCount >= 2) {
            ValidationResult(successful = true)
        } else {
            ValidationResult(
                successful = false,
                errorMessage = errorConstant
            )
        }
    }
}