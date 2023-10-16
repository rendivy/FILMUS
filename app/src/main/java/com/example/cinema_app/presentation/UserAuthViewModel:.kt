package com.example.cinema_app.presentation

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema_app.data.entity.RegistrationRequest
import com.example.cinema_app.data.entity.Token
import com.example.cinema_app.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserAuthViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

    private val _tokenBody = MutableStateFlow<Token>(Token("EMPTY_STRING"))
    val tokenBody: StateFlow<Token> = _tokenBody


    fun registerUser(
        userName: String,
        name: String,
        password: String,
        email: String,
    ) {
        viewModelScope.launch {
            try {
                _tokenBody.value = repository.registerUser(
                    RegistrationRequest(
                        userName = userName,
                        name = name,
                        password = password,
                        email = email,
                    )
                )
            } catch (e: Exception) {
                Log.d("TAG", "Exception during request -> ${e.localizedMessage}")
            }

        }

        Log.d(TAG, "Correct")
        Log.d(TAG, "Token: ${tokenBody.value.token}")


    }
}

