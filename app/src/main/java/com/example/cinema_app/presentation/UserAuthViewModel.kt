package com.example.cinema_app.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema_app.common.Constants
import com.example.cinema_app.data.converter.DateConverter
import com.example.cinema_app.data.entity.AuthenticationBody
import com.example.cinema_app.data.entity.RegistrationBody
import com.example.cinema_app.domain.usecase.RegisterUserUseCase
import com.example.cinema_app.presentation.validator.PasswordValidator
import com.example.cinema_app.ui.state.AuthenticationContent
import com.example.cinema_app.ui.state.RegistrationContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserAuthViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val dateConverter: DateConverter,
    private val passwordValidator: PasswordValidator
) :
    ViewModel() {

    val registrationState: State<RegistrationContent>
        get() = _registrationState

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvent = validationEventChannel.receiveAsFlow()


    private val _registrationState: MutableState<RegistrationContent> = mutableStateOf(
        RegistrationContent(
            login = Constants.EMPTY_STRING,
            name = Constants.EMPTY_STRING,
            password = Constants.EMPTY_STRING,
            email = Constants.EMPTY_STRING,
            confirmPassword = Constants.EMPTY_STRING,
        )
    )

    val loginState: State<AuthenticationContent>
        get() = _loginState

    private val _loginState: MutableState<AuthenticationContent> = mutableStateOf(
        AuthenticationContent(
            username = "rendivy",
            password = "310191",
        )
    )


    fun setAuthLogin(login: String) {
        _loginState.value = _loginState.value.copy(username = login)
    }

    fun setAuthPassword(password: String) {
        _loginState.value = _loginState.value.copy(password = password)
    }

    fun setName(name: String) {
        _registrationState.value = _registrationState.value.copy(name = name)
    }

    fun setEmail(email: String) {
        _registrationState.value = _registrationState.value.copy(email = email)
    }

    fun setPassword(password: String) {
        _registrationState.value = _registrationState.value.copy(password = password)
    }

    fun setLogin(login: String) {
        _registrationState.value = _registrationState.value.copy(login = login)
    }

    fun setUserGender(index: Int) {
        _registrationState.value = _registrationState.value.copy(gender = index)
    }

    fun setUserBirthdate(birthDate: Long?) {
        if (birthDate == null) return
        _registrationState.value = _registrationState.value.copy(
            birthDate = dateConverter.convertMillisToDateString(birthDate)
        )
    }

    fun loginUser() {
        viewModelScope.launch {
            try {
                registerUserUseCase.loginUser(
                    AuthenticationBody(
                        username = loginState.value.username,
                        password = loginState.value.password,
                    )
                )
            } catch (e: Exception) {
                Log.d("TAG", "registerUser: ${e.message}")
            }

        }
    }

    fun registerUser() {
        val passwordResult = passwordValidator.execute(registrationState.value.password)
        viewModelScope.launch {
            try {
                if (!passwordResult.successful) {
                    _registrationState.value = _registrationState.value.copy(
                        passwordError = passwordResult.errorMessage
                    )
                    return@launch
                }
                registerUserUseCase.registerUser(
                    RegistrationBody(
                        userName = registrationState.value.login,
                        name = registrationState.value.name,
                        password = registrationState.value.password,
                        email = registrationState.value.email,
                        birthDate = registrationState.value.birthDate,
                    )
                )
            } catch (e: Exception) {
                Log.d("TAG", "registerUser: ${e.message}")
            }

        }
    }
}

