package com.example.cinema_app.ui.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.cinema_app.common.Constants
import com.example.cinema_app.presentation.ProfileViewModel
import com.example.cinema_app.ui.component.switcher.TextSwitch
import com.example.cinema_app.ui.theme.shortPadding

@Composable
fun ProfileSwitcher(profileViewModel: ProfileViewModel, index: Int) {
    val items = remember {
        listOf(Constants.MALE_SWITCHER_VARIANT, Constants.FEMALE_SWITCHER_VARIANT)
    }
    val selectedIndex = remember { mutableStateOf(index) }

    Column(modifier = Modifier.padding(top = shortPadding)) {
        TextSwitch(
            selectedIndex = selectedIndex.value,
            items = items,
            onSelectionChange = {
                selectedIndex.value = it
                profileViewModel.setUserGender(selectedIndex.value)
            }
        )
    }
}