package com.example.cinema_app.ui.screen.login

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cinema_app.R
import com.example.cinema_app.presentation.LoginViewModel
import com.example.cinema_app.ui.navigation.NavigationRoutes
import com.example.cinema_app.ui.screen.login.component.LoginSection
import com.example.cinema_app.ui.theme.Accent
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.SecondarySemiBoldStyle
import com.example.cinema_app.ui.theme.ShortSpace
import com.example.cinema_app.ui.theme.TitleSmall

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(loginViewModel: LoginViewModel, navController: NavController) {
    val loginState by remember { loginViewModel.loginState }
    val focusManager = LocalFocusManager.current
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
                            modifier = Modifier.size(12.dp),
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
                    .background(color = Gray900),
                verticalArrangement = Arrangement.Top,
            ) {
                LoginSection(
                    loginState = loginState,
                    userAuthViewModel = loginViewModel,
                    focusManager = focusManager,
                    navController = navController
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    onClick = { loginViewModel.loginUser() },
                    contentPadding = PaddingValues(12.dp),
                    shape = RoundedCornerShape(size = 10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Accent
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.continue_label),
                        style = SecondarySemiBoldStyle
                    )
                }
            }

        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Gray900)
                    .padding(bottom = 16.dp),
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
