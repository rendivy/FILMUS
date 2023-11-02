package com.example.cinema_app.ui.screen.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.cinema_app.presentation.ProfileViewModel
import com.example.cinema_app.ui.theme.Accent
import com.example.cinema_app.ui.theme.Black300
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.TitleMedium
import java.time.LocalDateTime
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileAlert(checked: MutableState<Boolean>, profileViewModel: ProfileViewModel) {
    val datePickerState = remember {
        DatePickerState(
            yearRange = 1950..LocalDateTime.now().year,
            initialSelectedDateMillis = LocalDateTime.now().second.toLong(),
            initialDisplayedMonthMillis = null,
            initialDisplayMode = DisplayMode.Picker,
            locale = Locale.getDefault(),
        )
    }
    if (checked.value) {
        DatePickerDialog(
            colors = DatePickerDefaults.colors(
                currentYearContentColor = Gray900,
                containerColor = Gray900
            ),
            onDismissRequest = {
                checked.value = false
            },
            confirmButton = {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        modifier = Modifier
                            .align(Alignment.Center),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Black300
                        ),
                        onClick = {
                            checked.value = false
                            profileViewModel.setBirthDate(datePickerState.selectedDateMillis)
                        }) {
                        Text(
                            text = "Подтвердить",
                            style = TitleMedium,
                            color = Accent
                        )
                    }
                }
            }
        )
        {
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
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
}