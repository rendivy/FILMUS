package com.example.cinema_app.ui.screen.registration.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cinema_app.ui.theme.Gray900

@Composable
fun CustomClickableBox(checked: MutableState<Boolean>) {
    Box(
        modifier = Modifier
            .clickable { checked.value = checked.value.not() }
            .background(
                color = Gray900,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(8.dp)
            ).height(42.dp)
            .fillMaxWidth()
    ) {
        // You can add content to your box here if needed
    }
}