package com.example.cinema_app.ui.screen.registration.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cinema_app.presentation.validator.ErrorType
import com.example.cinema_app.ui.state.RegistrationContent
import com.example.cinema_app.ui.theme.TitleMedium


@Composable
fun RegistrationErrorAnimation(
    userState: RegistrationContent,
    errorType: ErrorType,
    outlinedColor: Color
) {
    lateinit var errorMessage: String

    when (errorType) {
        ErrorType.EMAIL -> errorMessage = userState.emailError!!
        ErrorType.PASSWORD -> errorMessage = userState.passwordError!!
        ErrorType.DATE -> errorMessage = userState.birthDateError!!
        ErrorType.LOGIN -> errorMessage = userState.loginError!!
        ErrorType.NAME -> errorMessage = userState.nameError!!
        ErrorType.CONFIRM_PASSWORD -> errorMessage = userState.confirmPasswordError!!
        else -> {}
    }
    AnimatedVisibility(visible = true, modifier = Modifier.padding(top = 8.dp)) {
        Text(
            text = errorMessage,
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            color = outlinedColor,
            style = TitleMedium,
        )
    }


}