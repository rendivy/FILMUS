package com.example.cinema_app.domain.validate

import com.example.cinema_app.presentation.common.ErrorConstant
import com.example.cinema_app.presentation.usecases.ValidateEmailUseCase
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * @author y.yangel
 */
internal class ValidateEmailUseCaseTest {

    @Test
    fun `WHEN email is valid THEN validation is successful`() {
        with(createEnvironment()) {
            val result = validateEmailUseCase("test.email@example.com")

            assertEquals(true, result.successful)
            assertEquals(null, result.errorMessage)
        }
    }

    @Test
    fun `WHEN email is empty THEN validation fails`() {
        with(createEnvironment()) {
            val result = validateEmailUseCase("")

            assertEquals(false, result.successful)
            assertEquals(ErrorConstant.EMAIL_ERROR, result.errorMessage)
        }
    }

    @Test
    fun `WHEN email does not contain '@' THEN validation fails`() {
        with(createEnvironment()) {
            val result = validateEmailUseCase("test.emailexample.com")

            assertEquals(false, result.successful)
            assertEquals(ErrorConstant.EMAIL_ERROR, result.errorMessage)
        }
    }

    @Test
    fun `WHEN email is too short THEN validation fails`() {
        with(createEnvironment()) {
            val result = validateEmailUseCase("a@b.c")

            assertEquals(false, result.successful)
            assertEquals(ErrorConstant.EMAIL_ERROR, result.errorMessage)
        }
    }

    @Test
    fun `WHEN email contains invalid characters THEN validation fails`() {
        with(createEnvironment()) {
            val result = validateEmailUseCase("test.email@ex@mple.com")

            assertEquals(false, result.successful)
            assertEquals(ErrorConstant.EMAIL_ERROR, result.errorMessage)
        }
    }

    private fun createEnvironment(): Environment {
        val validateEmailUseCase = ValidateEmailUseCase()
        return Environment(validateEmailUseCase)
    }

    private class Environment(
        val validateEmailUseCase: ValidateEmailUseCase,
    )
}