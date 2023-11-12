package com.example.cinema_app.ui.screen.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.cinema_app.R
import com.example.cinema_app.presentation.LoginViewModel
import com.example.cinema_app.presentation.state.LoginState
import com.example.cinema_app.ui.navigation.NavigationRoutes
import com.example.cinema_app.ui.screen.login.component.LoginSection
import com.example.cinema_app.ui.screen.registration.component.LoginErrorAnimation
import com.example.cinema_app.ui.theme.Accent
import com.example.cinema_app.ui.theme.Gray400
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.SecondarySemiBoldStyle
import com.example.cinema_app.ui.theme.ShortSpace
import com.example.cinema_app.ui.theme.TitleSmall
import com.example.cinema_app.ui.theme.padding10
import com.example.cinema_app.ui.theme.padding12
import com.example.cinema_app.ui.theme.padding16
import com.example.cinema_app.ui.theme.padding20
import com.example.cinema_app.ui.theme.padding8

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(loginViewModel: LoginViewModel, navController: NavController) {
    val loginState by remember { loginViewModel.loginState }
    val errorState by loginViewModel.errorState.collectAsStateWithLifecycle()
    var loginError by remember { mutableStateOf(false) }
    val enabled = loginViewModel.validateLoginCredentials()
    val focusManager = LocalFocusManager.current
    val buttonAlpha = if (enabled) 1f else 0.45f

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_logo),
                        color = Accent,
                        style = SecondarySemiBoldStyle,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                        navController.navigate(NavigationRoutes.Greetings.route)
                    }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.back_button_icon),
                            contentDescription = "back_icon_button",
                            modifier = Modifier.size(padding12),
                            tint = Color.White,
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Gray900,
                    scrolledContainerColor = Gray900,
                    navigationIconContentColor = Color.White,
                    titleContentColor = Accent
                ),
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(color = Gray900)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Top,
            ) {
                LoginSection(
                    loginState = loginState,
                    userAuthViewModel = loginViewModel,
                    focusManager = focusManager,
                    loginError = loginError,
                    navController = navController
                )
                Spacer(modifier = Modifier.height(padding20))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(buttonAlpha)
                        .padding(start = padding16, end = padding16),
                    onClick = { loginViewModel.loginUser() },
                    enabled = enabled,
                    contentPadding = PaddingValues(padding12),
                    shape = RoundedCornerShape(size = padding10),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Accent,
                        disabledContainerColor = Accent,
                    ),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        when (errorState) {
                            is LoginState.Error -> {
                                loginError = true
                            }

                            is LoginState.Success -> {
                                navController.navigate(NavigationRoutes.Main.route)
                            }

                            is LoginState.Loading -> {
                                AnimatedVisibility(visible = true) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center,
                                        modifier = Modifier.padding(end = padding8)
                                    ) {
                                        CircularProgressIndicator(
                                            modifier = Modifier.size(padding12),
                                            color = Color.White,
                                            trackColor = Gray400
                                        )
                                    }

                                }
                            }

                            else -> {}
                        }
                        Text(
                            text = stringResource(id = R.string.continue_label),
                            style = SecondarySemiBoldStyle
                        )
                    }
                }
                AnimatedVisibility(visible = loginState.uncorrectedUserName != null) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = padding16, end = padding16),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center){
                        loginState.uncorrectedUserName?.let { it1 ->
                            LoginErrorAnimation(
                                errorMessage = it1
                            )
                        }

                    }
                }
            }

        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Gray900)
                    .padding(bottom = padding16),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.dont_have_account),
                    style = TitleSmall
                )
                Spacer(modifier = Modifier.size(ShortSpace))
                Text(
                    text = stringResource(id = R.string.register_label),
                    modifier = Modifier.clickable(onClick = {
                        if (navController.previousBackStackEntry?.destination?.route == NavigationRoutes.Registration.route) {
                            navController.popBackStack()
                        } else {
                            navController.navigate(NavigationRoutes.Registration.route)
                        }
                    }),
                    color = Accent,
                    style = TitleSmall
                )
            }

        }
    )
}
