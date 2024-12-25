package com.example.cinema_app.presentation

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema_app.presentation.common.ErrorConstant
import com.example.cinema_app.presentation.state.LoginState
import com.example.cinema_app.presentation.usecases.ValidateLoginUseCase
import com.example.cinema_app.presentation.usecases.ValidatePasswordUseCase
import com.example.cinema_app.ui.state.LoginContent
import com.example.feature_filmus_movie_api.usecase.auth.LoginUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase,
    private val validateLoginUseCase: ValidateLoginUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) : ViewModel() {

    private val _errorState = MutableStateFlow<LoginState>(LoginState.Initial)
    val errorState: StateFlow<LoginState> = _errorState

    val loginContent: State<LoginContent>
        get() = _loginState

    private val _loginState: MutableState<LoginContent> = mutableStateOf(LoginContent())

    private val loginExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                400 -> {
                    _loginState.value =
                        loginContent.value.copy(uncorrectedUserName = ErrorConstant.UNAUTHORIZED)
                    _errorState.value = LoginState.Error(ErrorConstant.UNAUTHORIZED)
                }

                else -> {
                    _loginState.value =
                        loginContent.value.copy(uncorrectedUserName = ErrorConstant.UNEXPECTED_ERROR)
                    _errorState.value = LoginState.Error(ErrorConstant.UNKNOWN_ERROR)
                }
            }

            else -> {
                _loginState.value =
                    loginContent.value.copy(uncorrectedUserName = ErrorConstant.UNEXPECTED_ERROR)
                _errorState.value = LoginState.Error(ErrorConstant.UNKNOWN_ERROR)
            }

        }
    }

    fun loginUser() {
        _loginState.value = loginContent.value.copy(uncorrectedUserName = null)
        viewModelScope.launch(Dispatchers.IO + loginExceptionHandler) {
            _errorState.value = LoginState.Loading
            loginUserUseCase(
                login = loginContent.value.username,
                password = loginContent.value.password,
            )
            _errorState.value = LoginState.Success
        }
    }

    @VisibleForTesting
    fun validateLoginCredentials(): Boolean {
        return loginContent.value.passwordError == null &&
                loginContent.value.password.isNotEmpty() &&
                loginContent.value.usernameError == null &&
                loginContent.value.username.isNotEmpty()
    }

    fun setLogin(login: String) {
        _loginState.value = _loginState.value.copy(
            username = login,
            usernameError = validateLoginUseCase(login).errorMessage
        )
    }

    fun setPassword(password: String) {
        _loginState.value = _loginState.value.copy(
            password = password,
            passwordError = validatePasswordUseCase(password).errorMessage
        )
    }

}