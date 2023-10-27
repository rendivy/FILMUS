package com.example.cinema_app.domain.usecase

import com.example.cinema_app.common.ErrorConstant
import com.example.cinema_app.presentation.validator.ValidationResult

class ValidateEmailUseCase {

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