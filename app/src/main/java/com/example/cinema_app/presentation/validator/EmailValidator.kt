package com.example.cinema_app.presentation.validator

import com.example.cinema_app.common.ErrorConstant

class EmailValidator {

    private val emailPattern = ErrorConstant.EMAIL_PATTERN

    fun execute(email: String): ValidationResult {
        return if (!email.matches(emailPattern.toRegex())) {
            ValidationResult(
                successful = false,
                errorMessage = ErrorConstant.EMAIL_ERROR
            )
        } else {
            ValidationResult(
                successful = true,
            )
        }
    }
}