package com.example.cinema_app.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import com.example.cinema_app.R
import com.example.cinema_app.ui.navigation.NavigationRoutes
import kotlinx.coroutines.delay


@Composable
fun LaunchScreen(navController: NavController) {
    LaunchedEffect(key1 = true, block = {
        delay(2000)
        navController.popBackStack()
        navController.navigate(NavigationRoutes.Greetings.route)
    }
    )
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
