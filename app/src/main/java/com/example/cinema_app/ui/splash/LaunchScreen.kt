package com.example.cinema_app.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.cinema_app.R
import com.example.cinema_app.presentation.AuthViewModel
import com.example.cinema_app.ui.navigation.NavigationRoutes


@Composable
fun LaunchScreen(navController: NavController, authViewModel: AuthViewModel) {

    val token by authViewModel.tokenState.collectAsStateWithLifecycle()

    val startDestination: String = when (token) {
        true -> NavigationRoutes.Main.route
        false -> NavigationRoutes.Greetings.route
    }
    navController.popBackStack()
    navController.navigate(startDestination)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0, 0, 0))
            .paint(
                painter = painterResource(id = R.drawable.splash_screen),
                contentScale = ContentScale.Crop
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.app_logo),
            contentDescription = null
        )
    }
}
