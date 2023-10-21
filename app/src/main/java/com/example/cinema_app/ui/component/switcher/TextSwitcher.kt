package com.example.cinema_app.ui.component.switcher

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.cinema_app.common.Constants.DEFAULT_SEX_INDEX

@Preview
@Composable
fun TextSwitchTest() {
    val items = remember {
        listOf("Мужчина", "Женщина")
    }

    var selectedIndex by remember {
        mutableIntStateOf(DEFAULT_SEX_INDEX)
    }
    Log.d("TextSwitchTest", "selectedIndex: $selectedIndex")

    Column {
        TextSwitch(
            selectedIndex = selectedIndex,
            items = items,
            onSelectionChange = {
                selectedIndex = it
            }
        )
    }
}