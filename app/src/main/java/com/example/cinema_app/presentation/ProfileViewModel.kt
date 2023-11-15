package com.example.cinema_app.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema_app.common.Constants
import com.example.cinema_app.common.ErrorConstant
import com.example.cinema_app.presentation.converter.DateConverter
import com.example.cinema_app.data.entity.ProfileCredentials
import com.example.cinema_app.domain.usecase.GetUserProfileUseCase
import com.example.cinema_app.domain.usecase.LogoutUserUseCase
import com.example.cinema_app.domain.usecase.UpdateUserProfileUseCase
import com.example.cinema_app.domain.usecase.ValidateEmailUseCase
import com.example.cinema_app.presentation.state.ProfileState
import com.example.cinema_app.presentation.validator.ValidationResult
import com.example.cinema_app.ui.state.ProfileContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val updateUserProfileUseCase: UpdateUserProfileUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val dateUseCase: DateConverter,
    private val logoutUserUseCase: LogoutUserUseCase
) : ViewModel() {

    private val _credentialsState = MutableStateFlow<ProfileState>(ProfileState.Initial)
    val credentialsState: StateFlow<ProfileState> = _credentialsState

    val profileState: State<ProfileContent>
        get() = _profileState

    private val _profileState: MutableState<ProfileContent> = mutableStateOf(
        ProfileContent(
            name = Constants.EMPTY_STRING,
            email = Constants.EMPTY_STRING,
            birthDate = Constants.EMPTY_STRING,
            login = Constants.EMPTY_STRING,
            id = Constants.EMPTY_STRING,
            userAvatar = Constants.EMPTY_STRING,
            gender = 1
        )
    )


    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                401 -> {
                    _credentialsState.value = ProfileState.Error(ErrorConstant.UNAUTHORIZED)
                }
                400 -> {
                    _profileState.value =
                        _profileState.value.copy(emailError = ErrorConstant.UNIQUE_EMAIL)
                }
            }

            else -> {
                _credentialsState.value = ProfileState.Error(ErrorConstant.UNKNOWN_ERROR)
                _profileState.value =
                    _profileState.value.copy(unexpectedError = ErrorConstant.UNKNOWN_ERROR)
            }
        }
    }


    fun setUserGender(index: Int) {
        _profileState.value = _profileState.value.copy(gender = index)
    }

    fun setBirthDate(birthDate: Long?) {
        if (birthDate == null) return
        val convertDate = dateUseCase.convertMillisToDateString(birthDate)
        _profileState.value = _profileState.value.copy(
            birthDate = convertDate
        )
    }

    fun validateName(name: String): ValidationResult {
        if (name.isEmpty()) {
            return ValidationResult(false, "Имя не может быть пустым")
        } else {
            return ValidationResult(true)
        }
    }

    fun setName(name: String) {
        _profileState.value = _profileState.value.copy(
            name = name, nameError = validateName(name).errorMessage
        )
    }

    fun setEmail(email: String) {
        _profileState.value = _profileState.value.copy(
            email = email, emailError = validateEmail(email).errorMessage
        )

    }

    fun setUserAvatar(avatar: String) {
        _profileState.value = _profileState.value.copy(
            userAvatar = avatar
        )
    }

    fun updateUserProfile(callback: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            updateUserProfileUseCase.execute(
                ProfileCredentials(
                    avatarLink = _profileState.value.userAvatar,
                    birthDate = dateUseCase.convertUiDateToRemote(_profileState.value.birthDate),
                    email = _profileState.value.email,
                    gender = _profileState.value.gender,
                    name = _profileState.value.name,
                    id = _profileState.value.id,
                    nickName = _profileState.value.login
                )
            )
            _profileState.value = _profileState.value.copy(emailError = null)
            callback()
        }
    }


    private fun validateEmail(email: String): ValidationResult {
        return validateEmailUseCase.execute(email)
    }


    private fun setProfileContent(credentials: ProfileCredentials) {
        _profileState.value = _profileState.value.copy(
            name = credentials.name,
            gender = credentials.gender,
            email = credentials.email,
            birthDate = dateUseCase.convertDateToUi(credentials.birthDate),
            login = credentials.nickName,
            id = credentials.id,
            userAvatar = credentials.avatarLink
        )
    }

    fun logoutUser() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            logoutUserUseCase.execute()
        }
    }

    fun retry() {
        _credentialsState.value = ProfileState.Initial
    }

    fun getUserProfile() {
        viewModelScope.launch(exceptionHandler) {
            _credentialsState.value = ProfileState.Loading
            val credentials = getUserProfileUseCase.execute()
            setProfileContent(credentials)
            _credentialsState.value = ProfileState.Content(_profileState.value)

        }
    }
}


