package com.example.cinema_app.ui.screen.registration.credentials

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cinema_app.R
import com.example.cinema_app.presentation.RegistrationViewModel
import com.example.cinema_app.presentation.validator.ErrorType
import com.example.cinema_app.ui.component.CustomClickableBox
import com.example.cinema_app.ui.component.CustomTextField
import com.example.cinema_app.ui.component.switcher.TextSwitchTest
import com.example.cinema_app.ui.screen.profile.DatePickerAlert
import com.example.cinema_app.ui.screen.registration.component.RegistrationErrorAnimation
import com.example.cinema_app.ui.state.RegistrationContent
import com.example.cinema_app.ui.theme.Red
import com.example.cinema_app.ui.theme.SemiBoldStyle
import com.example.cinema_app.ui.theme.TitleSmall
import com.example.cinema_app.ui.theme.mediumPadding
import com.example.cinema_app.ui.theme.shortPadding

@Composable
fun RegistrationSection(
    userState: RegistrationContent,
    userAuthViewModel: RegistrationViewModel,
    focusManager: FocusManager
) {
    val checked = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = mediumPadding, end = mediumPadding)
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
            style = SemiBoldStyle,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(mediumPadding))
        Text(
            text = stringResource(id = R.string.name_label),
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            style = TitleSmall
        )
        Spacer(modifier = Modifier.height(shortPadding))
        CustomTextField(
            textFieldValue = userState.name,
            onValueChange = userAuthViewModel::setName,
            error = userState.nameError,

            )
        if (userState.nameError != null) {
            RegistrationErrorAnimation(
                userState = userState,
                errorType = ErrorType.NAME,
                outlinedColor = Red
            )
        }
        Spacer(modifier = Modifier.height(mediumPadding))
        Text(
            text = stringResource(id = R.string.user_sex_label),
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            style = TitleSmall
        )
        TextSwitchTest(userAuthViewModel)
        Spacer(modifier = Modifier.height(mediumPadding))
        Text(
            text = stringResource(id = R.string.login_label),
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            style = TitleSmall
        )
        Spacer(modifier = Modifier.height(shortPadding))
        CustomTextField(
            textFieldValue = userState.login,
            onValueChange = userAuthViewModel::setLogin,
            error = userState.loginError,
        )
        if (userState.loginError != null) {
            RegistrationErrorAnimation(
                userState = userState,
                errorType = ErrorType.LOGIN,
                outlinedColor = Red
            )
        }
        Spacer(modifier = Modifier.height(mediumPadding))
        Text(
            text = stringResource(id = R.string.email_label),
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            style = TitleSmall
        )
        Spacer(modifier = Modifier.height(shortPadding))
        CustomTextField(
            textFieldValue = userState.email,
            onValueChange = userAuthViewModel::setEmail,
            error = userState.emailError,
        )

        if (userState.emailError != null) {
            RegistrationErrorAnimation(
                userState = userState,
                errorType = ErrorType.EMAIL,
                outlinedColor = Red
            )
        }
        Spacer(modifier = Modifier.height(mediumPadding))
        Text(
            text = stringResource(id = R.string.date_label),
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            style = TitleSmall
        )
        Spacer(modifier = Modifier.height(shortPadding))
        CustomClickableBox(
            checked = checked,
            birth = userAuthViewModel.registrationContent.value.birthDate,
            error = userState.birthDateError,
        )
        if (userState.birthDateError != null) {
            RegistrationErrorAnimation(
                userState = userState,
                errorType = ErrorType.DATE,
                outlinedColor = Red
            )
        }
        DatePickerAlert(checked = checked, userAuthViewModel::setUserBirthdate)
    }
}