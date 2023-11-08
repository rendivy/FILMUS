package com.example.cinema_app.ui.component.switcher

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.cinema_app.common.Constants
import com.example.cinema_app.common.Constants.DEFAULT_SEX_INDEX
import com.example.cinema_app.presentation.RegistrationViewModel
import com.example.cinema_app.ui.theme.padding8


@Composable
fun TextSwitchTest(userAuthViewModel: RegistrationViewModel) {
    val items = remember {
        listOf(Constants.MALE_SWITCHER_VARIANT, Constants.FEMALE_SWITCHER_VARIANT)
    }

    var selectedIndex by remember {
        mutableIntStateOf(DEFAULT_SEX_INDEX)
    }

    Column(modifier = Modifier.padding(top = padding8)) {
        TextSwitch(
            selectedIndex = selectedIndex,
            items = items,
            onSelectionChange = {
                selectedIndex = it
                userAuthViewModel.setUserGender(selectedIndex)
            }
        )
    }
}