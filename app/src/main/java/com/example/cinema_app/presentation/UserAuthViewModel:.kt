package com.example.cinema_app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema_app.data.entity.RegistrationRequest
import com.example.cinema_app.data.entity.TokenBody
import com.example.cinema_app.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserAuthViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

    private val _tokenBody = MutableStateFlow<TokenBody?>(null)
    val tokenBody: StateFlow<TokenBody?> = _tokenBody


    fun registerUser(
        userName: String,
        name: String,
        password: String,
        email: String,
        birthDate: String = "2023-10-13T04:45:15.430Z",
    ) {
        viewModelScope.launch {
            try{
                _tokenBody.value = repository.registerUser(
                    RegistrationRequest(
                        userName = userName,
                        name = name,
                        password = password,
                        email = email,
                    )
                )
            }
            catch (_: Exception){

            }

        }
    }

}