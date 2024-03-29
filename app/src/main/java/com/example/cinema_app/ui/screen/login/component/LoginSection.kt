package com.example.cinema_app.ui.screen.login.component

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
import androidx.navigation.NavController
import com.example.cinema_app.R
import com.example.cinema_app.presentation.LoginViewModel
import com.example.cinema_app.ui.component.CustomTextField
import com.example.cinema_app.ui.component.PasswordTextField
import com.example.cinema_app.ui.screen.registration.component.LoginErrorAnimation
import com.example.cinema_app.ui.state.LoginContent
import com.example.cinema_app.ui.theme.TitleLarge
import com.example.cinema_app.ui.theme.TitleMedium
import com.example.cinema_app.ui.theme.mediumPadding
import com.example.cinema_app.ui.theme.shortPadding


@Composable
fun LoginSection(
    loginState: LoginContent,
    userAuthViewModel: LoginViewModel,
    focusManager: FocusManager,
    loginError: Boolean,
    navController: NavController
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
            text = stringResource(id = R.string.login_screen_label),
            modifier = Modifier.fillMaxWidth(),
            style = TitleLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(mediumPadding))
        Text(
            text = stringResource(id = R.string.login_label),
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp, bottom = shortPadding),
            color = Color.White,
            style = TitleMedium
        )
        CustomTextField(
            textFieldValue = loginState.username,
            onValueChange = userAuthViewModel::setLogin,
            error = loginState.usernameError
        )
        if (loginState.usernameError != null) {
            LoginErrorAnimation(errorMessage = loginState.usernameError)
        }
        Spacer(modifier = Modifier.height(mediumPadding))
        Text(
            text = stringResource(id = R.string.password_label),
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp, bottom = shortPadding),
            color = Color.White,
            style = TitleMedium
        )
        PasswordTextField(
            textFieldValue = loginState.password,
            onValueChange = userAuthViewModel::setPassword,
            error = loginState.passwordError
        )
        if (loginState.passwordError != null) {
            LoginErrorAnimation(errorMessage = loginState.passwordError)
        }



    }
}