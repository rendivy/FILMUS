package com.example.cinema_app.ui.screen.registration.utils

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.cinema_app.ui.theme.Accent
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.InterRegular
import java.time.LocalDateTime
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationSection(
    userState: RegistrationContent,
    userAuthViewModel: UserAuthViewModel,
    focusManager: FocusManager
) {
    var checked by remember { mutableStateOf(false) }
    val datePickerState = remember {
        DatePickerState(
            yearRange = 1950..LocalDateTime.now().year,
            initialSelectedDateMillis = LocalDateTime.now().second.toLong(),
            initialDisplayedMonthMillis = null,
            initialDisplayMode = DisplayMode.Picker,
            locale = Locale.getDefault(),
        )

    }
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
                fontFamily = InterRegular,
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
            fontFamily = InterRegular,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(8.dp))
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
            fontFamily = InterRegular,
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
            fontFamily = InterRegular,
            fontSize = 15.sp,
            textAlign = TextAlign.Start
        )
        CustomTextField(
            textFieldValue = userState.password,
            onValueChange = userAuthViewModel::setPassword
        )
        Button(onClick = { checked = !checked }) {
            Text(text = "Click me")
        }

        if (checked){
            DatePickerDialog(
                colors = DatePickerDefaults.colors(
                    currentYearContentColor = Gray900,
                ),
                onDismissRequest = {
                    checked = false
                },
                confirmButton = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Button(
                            modifier = Modifier.align(Alignment.Center),
                            onClick = {
                                checked = false

                            }) {
                        }

                    }

                }
            ) {
                DatePicker(
                    state = datePickerState, colors = DatePickerDefaults.colors(
                        titleContentColor = Accent,
                        headlineContentColor = Accent,
                        weekdayContentColor = Accent,
                        subheadContentColor = Accent,
                        yearContentColor = Accent,
                        currentYearContentColor = Gray900,
                        selectedYearContentColor = Gray900,
                        selectedYearContainerColor = Accent,
                        dayContentColor = Accent,
                        disabledDayContentColor = Accent,
                        selectedDayContainerColor = Accent,
                        disabledSelectedDayContainerColor = Accent,
                        todayContentColor = Accent,
                        todayDateBorderColor = Accent,
                        dayInSelectionRangeContentColor = Accent,
                        dayInSelectionRangeContainerColor = Accent
                    )
                )
            }
        }


        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.email_label),
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            color = Color.White,
            fontFamily = InterRegular,
            fontSize = 15.sp,
            textAlign = TextAlign.Start
        )
        CustomTextField(
            textFieldValue = userState.email,
            onValueChange = userAuthViewModel::setEmail
        )

    }
}