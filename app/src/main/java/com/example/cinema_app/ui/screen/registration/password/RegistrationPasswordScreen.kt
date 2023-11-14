package com.example.cinema_app.ui.screen.registration.password

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.cinema_app.R
import com.example.cinema_app.presentation.RegistrationViewModel
import com.example.cinema_app.presentation.state.LoginState
import com.example.cinema_app.presentation.state.RegistrationState
import com.example.cinema_app.ui.navigation.NavigationRoutes
import com.example.cinema_app.ui.screen.registration.component.LoginErrorAnimation
import com.example.cinema_app.ui.theme.Accent
import com.example.cinema_app.ui.theme.Gray400
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.SecondarySemiBoldStyle
import com.example.cinema_app.ui.theme.tinyPadding
import com.example.cinema_app.ui.theme.TitleSmall
import com.example.cinema_app.ui.theme.padding10
import com.example.cinema_app.ui.theme.semiMediumPadding
import com.example.cinema_app.ui.theme.mediumPadding
import com.example.cinema_app.ui.theme.padding20
import com.example.cinema_app.ui.theme.shortPadding


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationPasswordScreen(
    userAuthViewModel: RegistrationViewModel,
    navController: NavController
) {
    val registrationContent by remember { userAuthViewModel.registrationContent }
    val registrationState by userAuthViewModel.registrationState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    val enabled = !userAuthViewModel.isSecondRegistrationPageValid()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_logo),
                        color = Accent,
                        style = TitleSmall,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.back_button_icon),
                            contentDescription = "back_icon_button",
                            modifier = Modifier.size(width = 6.dp, height = semiMediumPadding),
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
                    .padding(it).pointerInput(Unit) {
                        detectTapGestures(onTap = {
                            focusManager.clearFocus()
                        })
                    }
                    .background(color = Gray900),
                verticalArrangement = Arrangement.Top,
            ) {
                RegistrationPasswordSection(
                    userState = registrationContent,
                    userAuthViewModel = userAuthViewModel,
                    focusManager = focusManager
                )
                Spacer(modifier = Modifier.height(padding20))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = mediumPadding, end = mediumPadding),
                    onClick = { userAuthViewModel.registerUser() },
                    enabled = enabled,
                    shape = RoundedCornerShape(size = padding10),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Accent
                    ),
                    contentPadding = PaddingValues(semiMediumPadding)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        when (registrationState) {

                            is RegistrationState.Success -> {
                                navController.navigate(NavigationRoutes.Main.route)
                            }

                            is RegistrationState.Loading -> {
                                AnimatedVisibility(visible = true) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center,
                                        modifier = Modifier.padding(end = shortPadding)
                                    ) {
                                        CircularProgressIndicator(
                                            modifier = Modifier.size(semiMediumPadding),
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
                AnimatedVisibility(visible = registrationContent.uniqueLoginError != null) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = mediumPadding, end = mediumPadding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center){
                        registrationContent.uniqueLoginError?.let { it1 ->
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
                    .padding(bottom = mediumPadding),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.already_registered),
                    style = TitleSmall
                )
                Spacer(modifier = Modifier.size(tinyPadding))
                Text(
                    text = stringResource(id = R.string.enter_button),
                    modifier = Modifier.clickable(
                        onClick = {
                            if (navController.previousBackStackEntry?.destination?.route == NavigationRoutes.Login.route) {
                                navController.popBackStack()
                            } else {
                                navController.navigate(NavigationRoutes.Login.route)
                            }
                        }),
                    color = Accent,
                    style = TitleSmall
                )
            }
        }
    )
}