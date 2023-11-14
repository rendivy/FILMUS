package com.example.cinema_app.ui.screen.registration.password

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import com.example.cinema_app.R
import com.example.cinema_app.presentation.RegistrationViewModel
import com.example.cinema_app.presentation.validator.ErrorType
import com.example.cinema_app.ui.component.PasswordTextField
import com.example.cinema_app.ui.screen.registration.component.RegistrationErrorAnimation
import com.example.cinema_app.ui.state.RegistrationContent
import com.example.cinema_app.ui.theme.Red
import com.example.cinema_app.ui.theme.TitleSmall
import com.example.cinema_app.ui.theme.mediumPadding
import com.example.cinema_app.ui.theme.shortPadding


@Composable
fun RegistrationPasswordSection(
    userState: RegistrationContent,
    userAuthViewModel: RegistrationViewModel,
    focusManager: FocusManager
) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = mediumPadding, end = mediumPadding)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(id = R.string.password_label),
            modifier = Modifier.fillMaxWidth(),
            style = TitleSmall,
        )
        Spacer(modifier = Modifier.height(shortPadding))
        PasswordTextField(
            textFieldValue = userState.password,
            error = userState.passwordError,
            onValueChange = userAuthViewModel::setPassword,
        )
        if (userState.passwordError != null) {
            RegistrationErrorAnimation(
                userState = userState,
                errorType = ErrorType.PASSWORD,
                outlinedColor = Red
            )
        }
        Spacer(modifier = Modifier.height(mediumPadding))
        Text(
            text = stringResource(id = R.string.repeat_password),
            modifier = Modifier.fillMaxWidth(),
            style = TitleSmall,
        )
        Spacer(modifier = Modifier.height(shortPadding))
        PasswordTextField(
            textFieldValue = userState.confirmPassword,
            error = userState.confirmPasswordError,
            onValueChange = userAuthViewModel::setConfirmPassword,
        )
        if (userState.confirmPasswordError != null) {
            RegistrationErrorAnimation(
                userState = userState,
                errorType = ErrorType.CONFIRM_PASSWORD,
                outlinedColor = Red
            )
        }

    }

}