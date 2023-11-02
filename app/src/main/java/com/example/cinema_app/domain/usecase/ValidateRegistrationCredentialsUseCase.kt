package com.example.cinema_app.domain.usecase

import androidx.compose.runtime.MutableState
import com.example.cinema_app.common.ErrorConstant
import com.example.cinema_app.ui.state.RegistrationContent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ValidateRegistrationCredentialsUseCase @Inject constructor(
    private val emailValidatorUseCase: ValidateEmailUseCase,
    private val validateLoginUseCase: ValidateLoginUseCase,
    private val dateValidatorUseCase: ValidateDateUseCase,
) {
    fun execute(registrationContent: MutableState<RegistrationContent>): Boolean {
        val emailResult = emailValidatorUseCase.execute(registrationContent.value.email)
        val loginResult = validateLoginUseCase.execute(registrationContent.value.login)
        val nameResult = validateLoginUseCase.execute(registrationContent.value.name)
        val dateResult = dateValidatorUseCase.execute(registrationContent.value.birthDate)

        val isEmailCorrect = emailResult.successful
        val isLoginCorrect = loginResult.successful
        val isDateCorrect = dateResult.successful
        val isNameCorrect = nameResult.successful

        registrationContent.value = registrationContent.value.copy(
            emailError = if (!isEmailCorrect) emailResult.errorMessage else null,
            loginError = if (!isLoginCorrect) loginResult.errorMessage else null,
            birthDateError = if (!isDateCorrect) dateResult.errorMessage else null,
            nameError = if (!isNameCorrect) ErrorConstant.NAME_ERROR else null,
        )

        return isEmailCorrect && isLoginCorrect && isDateCorrect && isNameCorrect
    }
}