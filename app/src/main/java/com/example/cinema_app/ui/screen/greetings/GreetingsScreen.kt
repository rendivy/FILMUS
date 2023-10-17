package com.example.cinema_app.ui.screen.greetings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cinema_app.R
import com.example.cinema_app.ui.theme.InterFontMedium
import com.example.cinema_app.ui.theme.backgroundPrimaryColor
import com.example.cinema_app.ui.theme.primaryActionColor
import com.example.cinema_app.ui.theme.secondaryContainerColor


@Composable
fun GreetingsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize().background(backgroundPrimaryColor)
            .padding(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Image(
            painter = painterResource(id = R.drawable.greetings_logo),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Погрузись в мир кино",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontFamily = InterFontMedium,
                fontWeight = FontWeight(700),
                fontSize = 20.sp,
                lineHeight = 24.sp,
                color = Color.White
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Мы предлагаем удобный и легкий способ насладиться любимыми фильмами прямо с Вашего мобильного устройства.",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontFamily = InterFontMedium,
                fontWeight = FontWeight(700),
                fontSize = 15.sp,
                lineHeight = 24.sp,
                color = Color.White
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                navController.popBackStack()
                navController.navigate("Registration")
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(size = 10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryActionColor
            )
        ) {
            Text(
                text = stringResource(id = R.string.main_registration),
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    fontFamily = InterFontMedium,
                    fontWeight = FontWeight(700),
                    fontSize = 15.sp,
                    color = Color.White
                ),
                textAlign = TextAlign.Center
            )
        }
        Button(
            onClick = {
                navController.popBackStack()
                navController.navigate("Registration")
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(size = 10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = secondaryContainerColor
            )
        ) {
            Text(
                text = "Войти",
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    fontFamily = InterFontMedium,
                    fontWeight = FontWeight(700),
                    fontSize = 15.sp,
                    color = primaryActionColor
                ),
                textAlign = TextAlign.Center
            )
        }
    }

}