package com.example.cinema_app.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinema_app.common.Constants
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.InterRegular


@Composable
fun CustomTextField(
    textFieldValue: String = Constants.EMPTY_STRING,
    outlined: Color = Color.Gray,
    error: String? = null,
    container: Color = Gray900,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    val outlinedColor = if (error != null) Color.Red else Color.Gray
    val containerColor = if (error != null) Color.Red.copy(alpha = 0.1f) else Gray900
    BasicTextField(
        modifier = Modifier
            .background(
                color = containerColor,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = outlinedColor,
                shape = RoundedCornerShape(8.dp)
            )
            .fillMaxWidth(),
        value = textFieldValue,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            fontSize = 15.sp, color = Color.White,
            fontFamily = InterRegular
        ),
        keyboardOptions = keyboardOptions,
        singleLine = true,
        enabled = true,
        cursorBrush = SolidColor(Color.White),
        decorationBox = @Composable { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
                ) {
                innerTextField()
            }
        }
    )
}