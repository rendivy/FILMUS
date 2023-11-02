package com.example.cinema_app.ui.screen.profile

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cinema_app.presentation.ProfileViewModel
import com.example.cinema_app.ui.component.switcher.TextSwitch

@Composable
fun ProfileSwitcher(profileViewModel: ProfileViewModel, index: Int) {
    val items = remember {
        listOf("Мужчина", "Женщина")
    }
    Log.d("ProfileSwitcher", "index: $index")
    val selectedIndex = remember { mutableStateOf(index) }

    Column(modifier = Modifier.padding(top = 8.dp)) {
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