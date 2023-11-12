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

    private val _errorState = MutableStateFlow<LoginState>(LoginState.Initial)
    val errorState: StateFlow<LoginState> = _errorState

    val loginState: State<LoginContent>
        get() = _loginState


    private val _loginState: MutableState<LoginContent> = mutableStateOf(
        LoginContent(
            username = Constants.EMPTY_STRING,
            password = Constants.EMPTY_STRING,
        )
    )

    private val loginExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                400 -> {
                    _loginState.value =
                        loginState.value.copy(uncorrectedUserName = ErrorConstant.UNAUTHORIZED)
                    _errorState.value = LoginState.Error(ErrorConstant.UNAUTHORIZED)
                }

                else -> {
                    _errorState.value = LoginState.Error(ErrorConstant.UNKNOWN_ERROR)
                }
            }
        }
    }

    fun loginUser() {
        viewModelScope.launch(Dispatchers.IO + loginExceptionHandler) {
            _errorState.value = LoginState.Loading
            loginUserUseCase.invoke(loginContent = loginState.value)
            _errorState.value = LoginState.Success
        }
    }

    fun validateLoginCredentials() : Boolean {
        return loginState.value.passwordError == null &&
                loginState.value.password.isNotEmpty() &&
                loginState.value.usernameError == null &&
                loginState.value.username.isNotEmpty()
    }

    fun setLogin(login: String) {
        _loginState.value = _loginState.value.copy(
            username = login,
            usernameError = validateLoginUseCase.execute(login).errorMessage
        )
    }

    fun setPassword(password: String) {
        _loginState.value = _loginState.value.copy(
            password = password,
            passwordError = validatePasswordUseCase.execute(password).errorMessage
        )
    }

}