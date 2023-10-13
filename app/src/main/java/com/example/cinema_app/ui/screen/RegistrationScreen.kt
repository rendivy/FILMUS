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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import com.example.cinema_app.ui.screen.common.CustomTextField
import com.example.cinema_app.ui.theme.backgroundPrimaryColor

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun RegistrationScreen() {
    val name = remember { mutableStateOf("") }
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
            text = "Имя",
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            color = Color.White,
            fontSize = 15.sp,
            textAlign = TextAlign.Start
        )
        CustomTextField(name)
    }
}