package com.example.cinema_app.ui.screen.registration

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cinema_app.R
import com.example.cinema_app.presentation.UserAuthViewModel
import com.example.cinema_app.ui.theme.InterFontBold
import com.example.cinema_app.ui.theme.InterFontMedium
import com.example.cinema_app.ui.theme.backgroundPrimaryColor
import com.example.cinema_app.ui.theme.primaryActionColor

@ExperimentalMaterial3Api
@Composable
fun RegistrationScreen(
    userAuthViewModel: UserAuthViewModel
) {
    val name = remember { mutableStateOf("yuuuuur1121312") }
    val username = remember { mutableStateOf("tyyyy123112312") }
    val password = remember { mutableStateOf("21312321121312") }
    val email = remember { mutableStateOf("arlosane7123213121123") }

    val tokenState by userAuthViewModel.tokenBody.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundPrimaryColor),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "FИЛЬМУС",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp),
            style = TextStyle(
                fontFamily = InterFontBold,
                fontWeight = FontWeight(800),
                fontSize = 24.sp,
                lineHeight = 24.sp,
                color = primaryActionColor
            ),
            textAlign = TextAlign.Center
        )
        FieldSection(
            username = username, name = name, password = password, email = email,
            focusManager = focusManager
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp),
            onClick = {
                userAuthViewModel.registerUser(
                    username.value, name.value, password.value, email.value
                )

            },
            shape = RoundedCornerShape(size = 10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryActionColor
            )
        ) {
            Text(
                text = stringResource(id = R.string.continue_label),
                style = TextStyle(
                    fontFamily = InterFontMedium,
                    fontWeight = FontWeight(700),
                    fontSize = 15.sp,
                    lineHeight = 24.sp,
                    color = Color.White
                )
            )
        }
        Text(
            text = tokenState.token, color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
    }


}