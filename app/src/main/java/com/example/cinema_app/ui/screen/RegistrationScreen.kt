package com.example.cinema_app.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinema_app.presentation.UserAuthViewModel
import com.example.cinema_app.ui.screen.common.CustomTextField
import com.example.cinema_app.ui.theme.backgroundPrimaryColor

@ExperimentalMaterial3Api
@Composable
fun RegistrationScreen(
    userAuthViewModel: UserAuthViewModel
) {
    val name = remember { mutableStateOf("tyyyyasd") }
    val username = remember { mutableStateOf("tyyyy") }
    val password = remember { mutableStateOf("21312321") }
    val email = remember { mutableStateOf("arlosane77") }

    val tokenBody by userAuthViewModel.tokenBody.collectAsState()
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundPrimaryColor)
            .padding(16.dp)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Имя",
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            color = Color.White,
            fontSize = 15.sp,
            textAlign = TextAlign.Start
        )
        CustomTextField(name)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Логин",
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            color = Color.White,
            fontSize = 15.sp,
            textAlign = TextAlign.Start
        )
        CustomTextField(username)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Пароль",
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            color = Color.White,
            fontSize = 15.sp,
            textAlign = TextAlign.Start
        )
        CustomTextField(password)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Email",
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            color = Color.White,
            fontSize = 15.sp,
            textAlign = TextAlign.Start
        )
        CustomTextField(email)
        Button(onClick = { userAuthViewModel.registerUser(
            username.value, name.value, password.value, email.value
        ) }) {
            Text(text = "Registration")
        }
        Text(
            text = "1231231",
            color = Color.White,
            fontSize = 24.sp
        )
        if (tokenBody?.authToken != null) {
            println(tokenBody!!.authToken)
            Text(
                text = "1231231",
                color = Color.White,
                fontSize = 24.sp
            )
        }
    }


}