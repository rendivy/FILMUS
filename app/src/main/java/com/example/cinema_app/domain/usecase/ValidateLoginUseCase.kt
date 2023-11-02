package com.example.cinema_app.domain.usecase

import com.example.cinema_app.common.ErrorConstant
import com.example.cinema_app.presentation.validator.ValidationResult

class ValidateLoginUseCase {
    fun execute(login: String): ValidationResult {
        return if (login.isEmpty()) {
            ValidationResult(
                successful = false,
                errorMessage = ErrorConstant.LOGIN_ERROR
            )
        } else {
            ValidationResult(
                successful = true,
            )
        }
    }
}