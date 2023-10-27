package com.example.cinema_app.presentation.validator

import com.example.cinema_app.common.ErrorConstant

class ConfirmPasswordValidator {
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