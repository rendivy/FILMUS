package com.example.cinema_app.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema_app.common.Constants
import com.example.cinema_app.data.converter.DateConverter
import com.example.cinema_app.data.entity.ProfileCredentials
import com.example.cinema_app.domain.usecase.GetUserProfileUseCase
import com.example.cinema_app.domain.usecase.UpdateUserProfileUseCase
import com.example.cinema_app.presentation.state.ProfileState
import com.example.cinema_app.ui.state.ProfileContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val updateUserProfileUseCase: UpdateUserProfileUseCase,
    private val dateUseCase: DateConverter
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
            userAvatar = Constants.EMPTY_STRING
        )
    )


    fun setUserGender(index: Int) {
        _profileState.value = _profileState.value.copy(gender = index)
    }

    fun setBirthDate(birthDate: Long?) {
        if (birthDate == null) return
        _profileState.value = _profileState.value.copy(
            birthDate = dateUseCase.convertMillisToDateString(birthDate)
        )
    }

    fun setName(name: String) {
        _profileState.value = _profileState.value.copy(
            name = name
        )
    }

    fun setEmail(email: String) {
        _profileState.value = _profileState.value.copy(
            email = email
        )
    }

    fun setUserAvatar(avatar: String) {
        _profileState.value = _profileState.value.copy(
            userAvatar = avatar
        )
    }

    fun updateUserProfile() {
        _credentialsState.value = ProfileState.Loading
        viewModelScope.launch {
            try {
                updateUserProfileUseCase.execute(
                    ProfileCredentials(
                        avatarLink = _profileState.value.userAvatar,
                        birthDate = _profileState.value.birthDate,
                        email = _profileState.value.email,
                        gender = _profileState.value.gender,
                        name = _profileState.value.name,
                        id = _profileState.value.id,
                        nickName = _profileState.value.login
                    )
                )
                _credentialsState.value = ProfileState.isSuccessful
            } catch (e: Exception) {
               Log.d("TAG", "updateUserProfile: ${e.message}")

            }
        }
    }

    fun setProfileContent(credentials: ProfileCredentials) {
        _profileState.value = _profileState.value.copy(
            name = credentials.name,
            email = credentials.email,
            birthDate = credentials.birthDate,
            login = credentials.nickName,
            id = credentials.id,
            userAvatar = credentials.avatarLink
        )
        Log.d("TAG", "setProfileContent: ${credentials.birthDate}")
    }

    fun getUserProfile() {
        viewModelScope.launch {

            val profileCredentials = getUserProfileUseCase.execute()
            setProfileContent(profileCredentials)

        }
    }
}


