package com.example.cinema_app.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema_app.common.Constants
import com.example.cinema_app.data.converter.DateConverter
import com.example.cinema_app.data.entity.RegistrationBody
import com.example.cinema_app.domain.usecase.RegisterUserUseCase
import com.example.cinema_app.domain.usecase.ValidateCofirmPasswordUseCase
import com.example.cinema_app.domain.usecase.ValidatePasswordUseCase
import com.example.cinema_app.domain.usecase.ValidateRegistrationCredentialsUseCase
import com.example.cinema_app.ui.state.RegistrationContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val dateConverter: DateConverter,
    private val passwordValidator: ValidatePasswordUseCase,
    private val ValidateRegister: ValidateRegistrationCredentialsUseCase,
    private val validateCofirmPasswordUseCase: ValidateCofirmPasswordUseCase
) :
    ViewModel() {

    val registrationState: State<RegistrationContent>
        get() = _registrationState


    private val _registrationState: MutableState<RegistrationContent> = mutableStateOf(
        RegistrationContent(
            login = Constants.EMPTY_STRING,
            name = Constants.EMPTY_STRING,
            password = Constants.EMPTY_STRING,
            email = Constants.EMPTY_STRING,
            confirmPassword = Constants.EMPTY_STRING,
        )
    )


    fun setConfirmPassword(confirmPassword: String) {
        _registrationState.value = _registrationState.value.copy(confirmPassword = confirmPassword)
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

    fun checkAllStates(): Boolean {
        return ValidateRegister.execute(_registrationState)
    }

    fun clearAllUserCredentials() {
        _registrationState.value = _registrationState.value.copy(
            login = Constants.EMPTY_STRING,
            name = Constants.EMPTY_STRING,
            password = Constants.EMPTY_STRING,
            email = Constants.EMPTY_STRING,
            confirmPassword = Constants.EMPTY_STRING,
            emailError = null,
            loginError = null,
            passwordError = null,
            confirmPasswordError = null,
            birthDateError = null,
            nameError = null,
        )
    }

    fun registerUser() {
        val passwordResult = passwordValidator.execute(_registrationState.value.password)
        val confirmPasswordResult = validateCofirmPasswordUseCase.execute(
            confirmPassword = _registrationState.value.confirmPassword,
            password = _registrationState.value.password
        )

        viewModelScope.launch {
            try {
                when {
                    !passwordResult.successful -> passwordResult.errorMessage?.let {
                        handlePasswordError(
                            it
                        )
                    }

                    else -> clearPasswordError()
                }

                when {
                    !confirmPasswordResult.successful -> confirmPasswordResult.errorMessage?.let {
                        handleConfirmPasswordError(
                            it
                        )
                    }

                    else -> clearConfirmPasswordError()
                }

                if (passwordResult.successful && confirmPasswordResult.successful) {
                    val registrationBody = createRegistrationBody()
                    registerUserUseCase.invoke(registrationBody)
                }
            }
            catch (e: Exception) {
                Log.d("TAG", "registerUser: ${e.message}")
            }
        }
    }

    private fun handlePasswordError(errorMessage: String) {
        _registrationState.value = _registrationState.value.copy(passwordError = errorMessage)
    }

    private fun clearPasswordError() {
        _registrationState.value = _registrationState.value.copy(passwordError = null)
    }

    private fun handleConfirmPasswordError(errorMessage: String) {
        _registrationState.value =
            _registrationState.value.copy(confirmPasswordError = errorMessage)
    }

    private fun clearConfirmPasswordError() {
        _registrationState.value = _registrationState.value.copy(confirmPasswordError = null)
    }

    private fun createRegistrationBody(): RegistrationBody {
        val registrationState = _registrationState.value
        return RegistrationBody(
            userName = registrationState.login,
            name = registrationState.name,
            password = registrationState.password,
            email = registrationState.email,
            birthDate = registrationState.birthDate
        )
    }
}

