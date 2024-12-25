package com.example.cinema_app.presentation.usecases

import com.example.cinema_app.presentation.common.ErrorConstant
import com.example.cinema_app.presentation.ValidationResult

class ValidateEmailUseCase {

    operator fun invoke(email: String): ValidationResult {
        return if (email.isNotEmpty() && !email.matches(EMAIL_PATTERN.toRegex())) {
            ValidationResult(
                successful = false,
                errorMessage = ErrorConstant.EMAIL_ERROR
            )
        } else {
            ValidationResult(successful = true)
        }
    }

    private companion object {
        const val EMAIL_PATTERN = "[a-zA-Z0-9._-]{4,}+@[a-z]+\\.+[a-z]{2,}+"
    }
}