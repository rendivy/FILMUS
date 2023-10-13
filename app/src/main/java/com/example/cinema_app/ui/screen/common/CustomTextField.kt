package com.example.cinema_app.ui.screen.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinema_app.ui.theme.backgroundPrimaryColor


@Composable
fun CustomTextField(userInput: MutableState<String>) {


    BasicTextField(
        modifier = Modifier
            .background(
                color = backgroundPrimaryColor,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(8.dp)
            )
            .fillMaxWidth()
            .height(45.dp),
        value = userInput.value,
        onValueChange = { userInput.value = it },
        textStyle = TextStyle(fontSize = 15.sp, color = Color.White),
        singleLine = true,
        cursorBrush = SolidColor(Color.White),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                innerTextField()
            }
        }
    )
}