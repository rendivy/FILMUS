package com.example.cinema_app.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema_app.common.ErrorConstant
import com.example.cinema_app.domain.usecase.auth.RegisterUserUseCase
import com.example.cinema_app.presentation.usecases.ValidateConfirmPasswordUseCase
import com.example.cinema_app.presentation.usecases.ValidateEmailUseCase
import com.example.cinema_app.presentation.usecases.ValidateLoginUseCase
import com.example.cinema_app.presentation.usecases.ValidatePasswordUseCase
import com.example.cinema_app.presentation.converter.DateConverter
import com.example.cinema_app.presentation.facade.ValidateRegistrationFacade
import com.example.cinema_app.presentation.state.RegistrationState
import com.example.cinema_app.ui.state.RegistrationContent
import com.example.cinema_app.ui.state.clearAllParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val dateUseCase: DateConverter,
    private val validateRegCredentialsUseCase: ValidateRegistrationFacade,
    private val validateConfirmPasswordUseCase: ValidateConfirmPasswordUseCase,
    private val validateLoginUseCase: ValidateLoginUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) : ViewModel() {

    private val _registrationState = MutableStateFlow<RegistrationState>(RegistrationState.Initial)
    val registrationState: StateFlow<RegistrationState> = _registrationState

    val registrationContent: State<RegistrationContent>
        get() = _registrationContent

    private val _registrationContent: MutableState<RegistrationContent> =
        mutableStateOf(RegistrationContent())

    private fun isPasswordValid(): Boolean {
        return _registrationContent.value.password.isNotEmpty()
                && _registrationContent.value.confirmPassword.isNotEmpty()
    }


    private fun isAnyCredentialNotEmpty(): Boolean {
        return _registrationContent.value.email.isNotEmpty() &&
                _registrationContent.value.login.isNotEmpty() &&
                _registrationContent.value.birthDate.isNotEmpty() &&
                _registrationContent.value.name.isNotEmpty()
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                400 -> {
                    _registrationContent.value =
                        _registrationContent.value.copy(uniqueLoginError = ErrorConstant.UNIQUE_LOGIN)
                    _registrationState.value = RegistrationState.Error(ErrorConstant.UNAUTHORIZED)
                }
            }

            else -> {
                _registrationContent.value =
                    _registrationContent.value.copy(uniqueLoginError = ErrorConstant.UNEXPECTED_ERROR)
            }
        }
    }

    fun setConfirmPassword(confirmPassword: String) {
        _registrationContent.value = _registrationContent.value.copy(
            confirmPassword = confirmPassword,
            confirmPasswordError = validateConfirmPasswordUseCase(
                password = _registrationContent.value.password,
                confirmPassword = confirmPassword
            ).errorMessage
        )
    }

    fun setName(name: String) {
        _registrationContent.value = _registrationContent.value.copy(
            name = name, nameError =
                validateLoginUseCase(name, ErrorConstant.NAME_ERROR).errorMessage
        )
    }

    fun setEmail(email: String) {
        _registrationContent.value = _registrationContent.value.copy(
            email = email,
            emailError = validateEmailUseCase(email).errorMessage,
        )
    }

    fun setPassword(password: String) {
        _registrationContent.value = _registrationContent.value.copy(
            password = password,
            passwordError = validatePasswordUseCase(password).errorMessage
        )
    }

    fun setLogin(login: String) {
        _registrationContent.value = _registrationContent.value.copy(
            login = login,
            loginError = validateLoginUseCase(login).errorMessage
        )
    }

    fun setUserGender(index: Int) {
        _registrationContent.value = _registrationContent.value.copy(gender = index)
    }


    fun setUserBirthdate(birthDate: Long?) {
        if (birthDate == null) return
        _registrationContent.value = _registrationContent.value.copy(
            birthDate = dateUseCase.convertMillisToDateString(birthDate)
        )
    }

    fun isFirstRegistrationPageValid(): Boolean {
        return (_registrationContent.value.emailError != null &&
                _registrationContent.value.loginError != null &&
                _registrationContent.value.birthDate.isNotEmpty() &&
                _registrationContent.value.nameError != null) ||
                !isAnyCredentialNotEmpty()
    }


    fun isSecondRegistrationPageValid(): Boolean {
        return (_registrationContent.value.passwordError != null ||
                _registrationContent.value.confirmPasswordError != null ||
                _registrationContent.value.uniqueLoginError != null) || !isPasswordValid()
    }

    fun checkAllStates(): Boolean {
        return validateRegCredentialsUseCase.execute(_registrationContent)
    }

    fun clearAllUserCredentials() {
        _registrationState.value = RegistrationState.Initial
        _registrationContent.value = _registrationContent.value.clearAllParams()
    }

    fun registerUser() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            _registrationState.value = RegistrationState.Loading
            createRegistrationParams()
            _registrationState.value = RegistrationState.Success
        }
    }

    private suspend fun createRegistrationParams() {
        registerUserUseCase(
            userName = _registrationContent.value.name,
            email = _registrationContent.value.email,
            birthDate = _registrationContent.value.birthDate,
            password = _registrationContent.value.password,
            name = _registrationContent.value.name,
            gender = _registrationContent.value.gender,
        )
        clearAllUserCredentials()
    }
}

