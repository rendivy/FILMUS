package com.example.cinema_app.presentation.facade

import androidx.compose.runtime.MutableState
import com.example.cinema_app.presentation.usecases.ValidateLoginUseCase
import com.example.cinema_app.presentation.usecases.ValidatePasswordUseCase
import com.example.cinema_app.ui.state.LoginContent
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ValidateLoginFacade @Inject constructor(
    private val validateLoginUseCase: ValidateLoginUseCase,
    private val passwordValidator: ValidatePasswordUseCase,
) {
    fun execute(loginContent: MutableState<LoginContent>): Boolean {
        val loginResult = validateLoginUseCase(loginContent.value.username)
        val passwordResult = passwordValidator(loginContent.value.password)

        val isLoginCorrect = loginResult.successful
        val isPasswordCorrect = passwordResult.successful


        loginContent.value = loginContent.value.copy(
            usernameError = if (!isLoginCorrect) loginResult.errorMessage else null,
            passwordError = if (!isPasswordCorrect) passwordResult.errorMessage else null,
        )
        return isLoginCorrect && isPasswordCorrect
    }
}