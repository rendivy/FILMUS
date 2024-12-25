package com.example.cinema_app.domain.validate
import com.example.cinema_app.presentation.common.ErrorConstant
import com.example.cinema_app.presentation.usecases.ValidatePasswordUseCase
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * @author y.yangel
 */
internal class ValidatePasswordUseCaseTest {

    @Test
    fun `WHEN password is valid THEN validation is successful`() {
        with(createEnvironment()) {
            val result = validatePasswordUseCase("validPassword")

            assertEquals(true, result.successful)
            assertEquals(null, result.errorMessage)
        }
    }

    @Test
    fun `WHEN password is too short THEN validation fails`() {
        with(createEnvironment()) {
            val result = validatePasswordUseCase("short")

            assertEquals(false, result.successful)
            assertEquals(ErrorConstant.PASSWORD_LENGTH_ERROR, result.errorMessage)
        }
    }

    @Test
    fun `WHEN password is empty THEN validation fails`() {
        with(createEnvironment()) {
            val result = validatePasswordUseCase("")

            assertEquals(false, result.successful)
            assertEquals(ErrorConstant.PASSWORD_LENGTH_ERROR, result.errorMessage)
        }
    }

    private fun createEnvironment(): Environment {
        val validatePasswordUseCase = ValidatePasswordUseCase()
        return Environment(validatePasswordUseCase)
    }

    private class Environment(
        val validatePasswordUseCase: ValidatePasswordUseCase,
    )
}