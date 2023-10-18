package com.example.cinema_app.ui.screen.greetings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cinema_app.R
import com.example.cinema_app.ui.navigation.NavigationRoutes
import com.example.cinema_app.ui.theme.Accent
import com.example.cinema_app.ui.theme.Gray400
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.SemiBold
import com.example.cinema_app.ui.theme.TitleLarge
import com.example.cinema_app.ui.theme.TitleSmall


@Composable
fun GreetingsScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray900)
            .padding(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Image(
            painter = painterResource(id = R.drawable.greetings_logo),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(35.dp))
        Text(
            text = stringResource(id = R.string.greetings_screen_label),
            modifier = Modifier.fillMaxWidth(),
            style = TitleLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.greetings_screen_source),
            letterSpacing = 0.05.sp,
            style = TitleSmall,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                navController.navigate(NavigationRoutes.Registration.route)
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(size = 10.dp),
            contentPadding = PaddingValues(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Accent
            )
        ) {
            Text(
                text = stringResource(id = R.string.main_registration),
                modifier = Modifier
                    .fillMaxWidth(),
                style = SemiBold,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Button(
            onClick = {
                navController.navigate(NavigationRoutes.Login.route)
            },

            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(size = 10.dp),
            contentPadding = PaddingValues(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Gray400
            )
        ) {
            Text(
                text = stringResource(id = R.string.enter_label),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                color = Accent,
                style = SemiBold,
                textAlign = TextAlign.Center
            )
        }
    }

}