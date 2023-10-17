package com.example.cinema_app.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema_app.common.Constants
import com.example.cinema_app.data.entity.RegistrationBody
import com.example.cinema_app.domain.usecase.RegistrationUseCase
import com.example.cinema_app.ui.state.RegistrationContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserAuthViewModel @Inject constructor(
    private val registrationUseCase: RegistrationUseCase
) : ViewModel() {
    val registrationState: State<RegistrationContent>
        get() = _registrationState

    private val _registrationState: MutableState<RegistrationContent> = mutableStateOf(
        RegistrationContent(
            login = Constants.EMPTY_STRING,
            name = Constants.EMPTY_STRING,
            password = Constants.EMPTY_STRING,
            email = Constants.EMPTY_STRING,
        )
    )

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


    fun registerUser() {
        viewModelScope.launch {
            registrationUseCase.registerUser(
                RegistrationBody(
                    userName = registrationState.value.login,
                    name = registrationState.value.name,
                    password = registrationState.value.password,
                    email = registrationState.value.email,
                )
            )
        }

    }
}

