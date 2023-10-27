package com.example.cinema_app.ui.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cinema_app.common.Constants
import com.example.cinema_app.presentation.ProfileViewModel
import com.example.cinema_app.ui.component.switcher.TextSwitch

@Composable
fun ProfileSwitcher(profileViewModel: ProfileViewModel) {
    val items = remember {
        listOf("Мужчина", "Женщина")
    }

    var selectedIndex by remember {
        mutableIntStateOf(Constants.DEFAULT_SEX_INDEX)
    }

    Column(modifier = Modifier.padding(top = 8.dp)) {
        TextSwitch(
            selectedIndex = selectedIndex,
            items = items,
            onSelectionChange = {
                selectedIndex = it
                profileViewModel.setUserGender(selectedIndex)
            }
        )
    }
}