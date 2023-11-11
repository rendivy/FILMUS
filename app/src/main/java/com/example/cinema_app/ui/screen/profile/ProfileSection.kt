package com.example.cinema_app.ui.screen.profile

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.ui.unit.dp
import com.example.cinema_app.R
import com.example.cinema_app.presentation.ProfileViewModel
import com.example.cinema_app.ui.component.CustomClickableBox
import com.example.cinema_app.ui.component.CustomTextField
import com.example.cinema_app.ui.state.ProfileContent
import com.example.cinema_app.ui.theme.Red
import com.example.cinema_app.ui.theme.TitleMedium
import com.example.cinema_app.ui.theme.TitleSmall


@Composable
fun ProfileSection(
    focusManager: FocusManager,
    userState: ProfileContent,
    profileViewModel: ProfileViewModel
) {
    val checked = remember { mutableStateOf<Boolean>(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(id = R.string.email_label),
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            style = TitleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomTextField(
            textFieldValue = userState.email,
            onValueChange = profileViewModel::setEmail,
            error = userState.emailError,
        )
        AnimatedVisibility(visible = userState.emailError != null){
            Text(
                text = "Такой email уже используется другим аккаунтом",
                modifier = Modifier.fillMaxWidth().padding(2.dp),
                color = Red,
                style = TitleMedium
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.name_label),
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            style = TitleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomTextField(
            textFieldValue = userState.name,
            onValueChange = profileViewModel::setName,
            error = userState.nameError
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Ссылка на аватар",
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            style = TitleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomTextField(
            textFieldValue = userState.userAvatar ?: "",
            onValueChange = { profileViewModel.setUserAvatar(it) },
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.user_sex_label),
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            style = TitleMedium
        )
        ProfileSwitcher(profileViewModel = profileViewModel, index = userState.gender)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.date_label),
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            style = TitleSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomClickableBox(
            checked = checked,
            birth = userState.birthDate,

            )
        ProfileAlert(checked = checked, profileViewModel = profileViewModel)
    }
}