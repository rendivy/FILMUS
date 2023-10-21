package com.example.cinema_app.ui.screen.registration

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
import com.example.cinema_app.presentation.UserAuthViewModel
import com.example.cinema_app.ui.navigation.NavigationRoutes
import com.example.cinema_app.ui.theme.Accent
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.SecondarySemiBoldStyle
import com.example.cinema_app.ui.theme.ShortSpace
import com.example.cinema_app.ui.theme.TitleSmall

@ExperimentalMaterial3Api
@Composable
fun RegistrationScreen(
    userAuthViewModel: UserAuthViewModel,
    navController: NavController
) {
    val registrationState by remember { userAuthViewModel.registrationState }
    val focusManager = LocalFocusManager.current
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
                        navController.navigate(NavigationRoutes.Greetings.route)
                    }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.back_button_icon),
                            contentDescription = "back_icon_button",
                            modifier = Modifier.size(width = 6.dp, height = 12.dp),
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

                RegistrationSection(
                    userState = registrationState,
                    userAuthViewModel = userAuthViewModel,
                    focusManager = focusManager
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    onClick = { navController.navigate(NavigationRoutes.RegistrationPasswordScreen.route) },
                    shape = RoundedCornerShape(size = 10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Accent
                    ),
                    contentPadding = PaddingValues(12.dp)
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
                    text = stringResource(id = R.string.already_registered),
                    style = TitleSmall
                )
                Spacer(modifier = Modifier.size(ShortSpace))
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

