package com.example.cinema_app.presentation


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema_app.common.ErrorConstant
import com.example.cinema_app.domain.usecase.LoginUserUseCase
import com.example.cinema_app.presentation.validator.LoginValidator
import com.example.cinema_app.presentation.validator.PasswordValidator
import com.example.cinema_app.ui.screen.login.state.LoginState
import com.example.cinema_app.ui.state.LoginContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase,
    private val loginValidator: LoginValidator,
    private val passwordValidator: PasswordValidator
) : ViewModel() {

    private val _errorState = MutableStateFlow<LoginState>(LoginState.Initial)
    val errorState: StateFlow<LoginState> = _errorState

    val loginState: State<LoginContent>
        get() = _loginState

    private val _loginState: MutableState<LoginContent> = mutableStateOf(
        LoginContent(
            username = "rendivy",
            password = "310191",
        )
    )

    private fun checkPasswordCorrect(): Boolean {
        val passwordResult = passwordValidator.execute(_loginState.value.password)
        if (!passwordResult.successful) {
            _loginState.value = _loginState.value.copy(
                passwordError = passwordResult.errorMessage
            )
            return false
        }
        _loginState.value = _loginState.value.copy(
            passwordError = null
        )
        return true

    }

    private fun checkLoginCorrect(): Boolean {
        val loginResult = loginValidator.execute(_loginState.value.username)
        if (!loginResult.successful) {
            _loginState.value = _loginState.value.copy(
                usernameError = loginResult.errorMessage
            )
            return false
        }
        _loginState.value = _loginState.value.copy(
            usernameError = null
        )
        return true

    }

    private val loginExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                400 -> {
                    _errorState.value = LoginState.Error(ErrorConstant.AUTHORIZATION_ERROR)
                }

                else -> {
                    _errorState.value = LoginState.Error(ErrorConstant.UNKNOWN_ERROR)
                }
            }
        }
    }

    fun loginUser() {
        viewModelScope.launch(loginExceptionHandler) {
            _errorState.value = LoginState.Loading

            if (!checkLoginCorrect() || !checkPasswordCorrect()) {
                _errorState.value = LoginState.Initial
                return@launch
            }
            loginUserUseCase.invoke(_loginState.value)
            _errorState.value = LoginState.Success
        }
    }

    fun setAuthLogin(login: String) {
        _loginState.value = _loginState.value.copy(username = login)
    }

    fun setAuthPassword(password: String) {
        _loginState.value = _loginState.value.copy(password = password)
    }

}