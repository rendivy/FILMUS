package com.example.cinema_app.ui.screen.login.component

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.cinema_app.R
import com.example.cinema_app.presentation.LoginViewModel
import com.example.cinema_app.presentation.state.LoginState
import com.example.cinema_app.ui.component.CustomTextField
import com.example.cinema_app.ui.component.PasswordTextField
import com.example.cinema_app.ui.navigation.NavigationRoutes
import com.example.cinema_app.ui.screen.registration.component.LoginErrorAnimation
import com.example.cinema_app.ui.state.LoginContent
import com.example.cinema_app.ui.theme.Accent
import com.example.cinema_app.ui.theme.Gray400
import com.example.cinema_app.ui.theme.TitleLarge
import com.example.cinema_app.ui.theme.TitleMedium


@Composable
fun LoginSection(
    loginState: LoginContent,
    userAuthViewModel: LoginViewModel,
    focusManager: FocusManager,
    navController: NavController
) {
    val errorState by userAuthViewModel.errorState.collectAsStateWithLifecycle()


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
            error = loginState.usernameError
        )
        if (loginState.usernameError != null) {
            LoginErrorAnimation(errorMessage = loginState.usernameError)
        }
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
            onValueChange = userAuthViewModel::setAuthPassword,
            error = loginState.passwordError
        )
        if (loginState.passwordError != null) {
            LoginErrorAnimation(errorMessage = loginState.passwordError)
        }
        Spacer(modifier = Modifier.height(8.dp))
        when (errorState) {
            is LoginState.Error -> {
                val errorMessage = (errorState as LoginState.Error).message
                Log.d("LoginSection", errorMessage)
                LoginErrorAnimation(errorMessage)
            }

            is LoginState.Success -> {
                navController.navigate(NavigationRoutes.Main.route)

            }

            is LoginState.Loading -> {
                AnimatedVisibility(visible = true) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = Accent,
                            trackColor = Gray400
                        )
                    }

                }
            }

            else -> {}

        }
        Spacer(modifier = Modifier.height(20.dp))


    }
}