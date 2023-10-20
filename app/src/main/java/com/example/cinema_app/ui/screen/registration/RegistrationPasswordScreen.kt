package com.example.cinema_app.ui.screen.registration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.example.cinema_app.presentation.UserAuthViewModel


@Composable
fun RegistrationPasswordScreen(userAuthViewModel: UserAuthViewModel) {
    val passwordState by remember { userAuthViewModel.registrationState }


}