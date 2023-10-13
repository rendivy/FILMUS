package com.example.cinema_app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema_app.data.entity.RegistrationRequest
import com.example.cinema_app.data.remote.MovieApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserAuthViewModel @Inject constructor(private val service: MovieApiService) : ViewModel() {


    fun registerUser(
        userName: String,
        name: String,
        password: String,
        email: String,
        birthDate: String,
        genderInt: Int = 0
    ) {
        viewModelScope.launch{
            service.register(RegistrationRequest(userName, name, password, email, birthDate, genderInt))
        }
    }


}