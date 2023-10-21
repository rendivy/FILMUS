package com.example.cinema_app.ui.screen.registration

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinema_app.R
import com.example.cinema_app.presentation.UserAuthViewModel
import com.example.cinema_app.ui.component.CustomTextField
import com.example.cinema_app.ui.theme.InterRegular


@Composable
fun RegistrationPasswordScreen(userAuthViewModel: UserAuthViewModel) {
    val registrationState by remember { userAuthViewModel.registrationState }
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = stringResource(id = R.string.password_label),
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        color = Color.White,
        fontFamily = InterRegular,
        fontSize = 15.sp,
        textAlign = TextAlign.Start
    )
    Spacer(modifier = Modifier.height(8.dp))
    CustomTextField(
        textFieldValue = registrationState.password,
        onValueChange = userAuthViewModel::setPassword
    )


}