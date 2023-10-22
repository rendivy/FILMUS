package com.example.cinema_app.presentation.validator

import com.example.cinema_app.common.ErrorConstant

class LoginValidator {
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