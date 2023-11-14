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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.example.cinema_app.ui.theme.Black300
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.SecondaryAccentStyle
import com.example.cinema_app.ui.theme.SemiBoldStyle
import com.example.cinema_app.ui.theme.TitleSmall
import com.example.cinema_app.ui.theme.padding10
import com.example.cinema_app.ui.theme.semiMediumPadding
import com.example.cinema_app.ui.theme.mediumPadding


@Composable
fun GreetingsScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize().verticalScroll(rememberScrollState())
            .background(Gray900)
            .padding(start = mediumPadding, end = mediumPadding),
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
            style = SemiBoldStyle,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.greetings_screen_source),
            letterSpacing = 0.05.sp,
            style = TitleSmall,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(mediumPadding))
        Button(
            onClick = {
                navController.navigate(NavigationRoutes.Registration.route)
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(size = padding10),
            contentPadding = PaddingValues(semiMediumPadding),
            colors = ButtonDefaults.buttonColors(
                containerColor = Accent
            )
        ) {
            Text(
                text = stringResource(id = R.string.main_registration),
                modifier = Modifier
                    .fillMaxWidth(),
                style = SecondaryAccentStyle,
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
            shape = RoundedCornerShape(size = padding10),
            contentPadding = PaddingValues(semiMediumPadding),
            colors = ButtonDefaults.buttonColors(
                containerColor = Black300
            )
        ) {
            Text(
                text = stringResource(id = R.string.enter_label),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                color = Accent,
                style = SecondaryAccentStyle,
                textAlign = TextAlign.Center
            )
        }
    }

}