package com.example.cinema_app.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema_app.domain.usecase.IsUserLoggedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val isUserLoggedUseCase: IsUserLoggedUseCase) :
    ViewModel() {

    private val _tokenState: MutableState<Boolean> = mutableStateOf(false)

    val tokenState: State<Boolean>
        get() = _tokenState

    fun isUserLogged() {
        viewModelScope.launch {
           _tokenState.value = isUserLoggedUseCase.execute()
        }
    }

}