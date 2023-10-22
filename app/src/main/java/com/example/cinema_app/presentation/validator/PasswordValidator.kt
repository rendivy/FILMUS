package com.example.cinema_app.presentation.validator

import com.example.cinema_app.common.ErrorConstant

class PasswordValidator {

    fun execute(password: String): ValidationResult {
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