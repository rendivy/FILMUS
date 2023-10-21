package com.example.cinema_app.ui.screen.registration

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cinema_app.R
import com.example.cinema_app.presentation.UserAuthViewModel
import com.example.cinema_app.ui.component.CustomTextField
import com.example.cinema_app.ui.state.RegistrationContent
import com.example.cinema_app.ui.theme.TitleMedium
import com.example.cinema_app.ui.theme.TitleSmall


@Composable
fun RegistrationPasswordSection(
    userState: RegistrationContent,
    userAuthViewModel: UserAuthViewModel,
    focusManager: FocusManager
) {
    val passwordFieldColor = if (userState.passwordError != null) {
        Color.Red
    } else {
        Color.Gray
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
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
        Spacer(modifier = Modifier.height(8.dp))
        CustomTextField(
            textFieldValue = userState.password,
            color = passwordFieldColor,
            onValueChange = userAuthViewModel::setPassword,
        )
        if (userState.passwordError != null) {
            AnimatedVisibility(visible = true) {
                Text(
                    text = userState.passwordError,
                    modifier = Modifier.fillMaxWidth().padding(2.dp),
                    color = passwordFieldColor,
                    style = TitleMedium,
                )
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
    }

}