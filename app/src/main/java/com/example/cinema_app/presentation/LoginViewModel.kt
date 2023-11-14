package com.example.cinema_app.presentation


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema_app.common.Constants
import com.example.cinema_app.common.ErrorConstant
import com.example.cinema_app.domain.usecase.LoginUserUseCase
import com.example.cinema_app.domain.usecase.ValidateLoginUseCase
import com.example.cinema_app.domain.usecase.ValidatePasswordUseCase
import com.example.cinema_app.presentation.state.LoginState
import com.example.cinema_app.ui.state.LoginContent
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

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Initial)
    val loginState: StateFlow<LoginState> = _loginState

    val loginContent: State<LoginContent>
        get() = _loginContent


    private val _loginContent: MutableState<LoginContent> = mutableStateOf(
        LoginContent(
            username = Constants.EMPTY_STRING,
            password = Constants.EMPTY_STRING,
        )
    )

    private val loginExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                400 -> {
                    _loginContent.value =
                        loginContent.value.copy(uncorrectedUserName = ErrorConstant.UNAUTHORIZED)
                    _loginState.value = LoginState.Error(ErrorConstant.UNAUTHORIZED)
                }

                else -> {
                    _loginState.value = LoginState.Error(ErrorConstant.UNKNOWN_ERROR)
                }
            }
        }
    }

    fun loginUser() {
        viewModelScope.launch(Dispatchers.IO + loginExceptionHandler) {
            _loginState.value = LoginState.Loading
            loginUserUseCase.invoke(loginContent = loginContent.value)
            _loginState.value = LoginState.Success
        }
    }

    fun validateLoginCredentials() : Boolean {
        return loginContent.value.passwordError == null &&
                loginContent.value.password.isNotEmpty() &&
                loginContent.value.usernameError == null &&
                loginContent.value.username.isNotEmpty()
    }

    fun setLogin(login: String) {
        _loginContent.value = _loginContent.value.copy(
            username = login,
            usernameError = validateLoginUseCase.execute(login).errorMessage
        )
    }

    fun setPassword(password: String) {
        _loginContent.value = _loginContent.value.copy(
            password = password,
            passwordError = validatePasswordUseCase.execute(password).errorMessage
        )
    }

}