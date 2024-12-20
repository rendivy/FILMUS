package com.example.cinema_app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema_app.domain.usecase.auth.IsUserLoggedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val isUserLoggedUseCase: IsUserLoggedUseCase
) : ViewModel() {

    private val _tokenState: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val tokenState: StateFlow<Boolean>
        get() = _tokenState.asStateFlow()

    fun isUserLogged() {
        viewModelScope.launch {
            _tokenState.value = isUserLoggedUseCase()
        }
    }

}