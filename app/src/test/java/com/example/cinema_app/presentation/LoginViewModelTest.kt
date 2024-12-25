package com.example.cinema_app.presentation

import com.example.cinema_app.presentation.common.ErrorConstant.PASSWORD_LENGTH_ERROR
import com.example.cinema_app.presentation.usecases.ValidateLoginUseCase
import com.example.cinema_app.presentation.usecases.ValidatePasswordUseCase
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock

/**
 * @author y.yangel
 */
internal class LoginViewModelTest {

    @Test
    fun `WHEN valid login credentials THEN validation is successful`() {
        with(createEnvironment()) {
            viewModel.setLogin("string")
            viewModel.setPassword("string88")

            assertEquals(viewModel.loginContent.value.usernameError, null)
            assertEquals(viewModel.loginContent.value.passwordError, null)
        }
    }

    @Test
    fun `WHEN space in login credentials THEN validation is invalid`() {
        with(createEnvironment()) {
            viewModel.setLogin("   ")
            viewModel.setPassword("   ")

            assertEquals(
                viewModel.loginContent.value.usernameError,
                "Логин не должен быть пустым и содержать не менее двух латинских символов"
            )
            assertEquals(viewModel.loginContent.value.passwordError, PASSWORD_LENGTH_ERROR)
        }
    }

    private fun createEnvironment(): Environment {
        val validateLoginUseCase = ValidateLoginUseCase()
        val validatePasswordUseCase = ValidatePasswordUseCase()

        val viewModel = LoginViewModel(
            loginUserUseCase = mock(),
            validateLoginUseCase = validateLoginUseCase,
            validatePasswordUseCase = validatePasswordUseCase,
        )
        return Environment(viewModel = viewModel)
    }

    class Environment(val viewModel: LoginViewModel)
}