package com.example.cinema_app.ui.screen.registration

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinema_app.R
import com.example.cinema_app.presentation.UserAuthViewModel
import com.example.cinema_app.ui.common.CustomTextField
import com.example.cinema_app.ui.state.RegistrationContent
import com.example.cinema_app.ui.theme.InterFontMedium

@Composable
fun FieldSection(
    userState: RegistrationContent,
    userAuthViewModel: UserAuthViewModel,
    focusManager: FocusManager
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(id = R.string.main_registration),
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
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.name_label),
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            color = Color.White,
            fontSize = 15.sp,
            fontFamily = InterFontMedium,
            textAlign = TextAlign.Start
        )
        CustomTextField(
            textFieldValue = userState.name,
            onValueChange = userAuthViewModel::setName,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.login_label),
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            color = Color.White,
            fontFamily = InterFontMedium,
            fontSize = 15.sp,
            textAlign = TextAlign.Start
        )
        CustomTextField(
            textFieldValue = userState.login,
            onValueChange = userAuthViewModel::setLogin,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.password_label),
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            color = Color.White,
            fontFamily = InterFontMedium,
            fontSize = 15.sp,
            textAlign = TextAlign.Start
        )
        CustomTextField(
            textFieldValue = userState.password,
            onValueChange = userAuthViewModel::setPassword
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.email_label),
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            color = Color.White,
            fontFamily = InterFontMedium,
            fontSize = 15.sp,
            textAlign = TextAlign.Start
        )
        CustomTextField(
            textFieldValue = userState.email,
            onValueChange = userAuthViewModel::setEmail
        )

    }
}