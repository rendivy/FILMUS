package com.example.cinema_app.ui.screen.login.utils

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cinema_app.R
import com.example.cinema_app.presentation.UserAuthViewModel
import com.example.cinema_app.ui.common.CustomTextField
import com.example.cinema_app.ui.common.PasswordTextField
import com.example.cinema_app.ui.state.AuthenticationContent
import com.example.cinema_app.ui.theme.TitleLarge
import com.example.cinema_app.ui.theme.TitleMedium


@Composable
fun LoginSection(
    loginState: AuthenticationContent,
    userAuthViewModel: UserAuthViewModel,
    focusManager: FocusManager
) {
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
            text = stringResource(id = R.string.login_screen_label),
            modifier = Modifier.fillMaxWidth(),
            style = TitleLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.login_label),
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp, bottom = 8.dp),
            color = Color.White,
            style = TitleMedium
        )
        CustomTextField(
            textFieldValue = loginState.username,
            onValueChange = userAuthViewModel::setAuthLogin,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.password_label),
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp, bottom = 8.dp),
            color = Color.White,
            style = TitleMedium
        )
        PasswordTextField(
            textFieldValue = loginState.password,
            onValueChange = userAuthViewModel::setAuthPassword
        )
        Spacer(modifier = Modifier.height(20.dp))


    }
}