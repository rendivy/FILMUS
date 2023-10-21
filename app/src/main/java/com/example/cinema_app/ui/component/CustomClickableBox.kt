package com.example.cinema_app.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cinema_app.R
import com.example.cinema_app.presentation.UserAuthViewModel
import com.example.cinema_app.ui.theme.Gray400
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.TitleMedium

@Composable
fun CustomClickableBox(checked: MutableState<Boolean>, userAuthViewModel: UserAuthViewModel) {

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
            )
            .height(42.dp)
            .fillMaxWidth()
    ) {

        Text(
            text = userAuthViewModel.registrationState.value.birthDate,
            modifier = Modifier.align(Alignment.CenterStart).fillMaxWidth().padding(12.dp),
            style = TitleMedium,
        )
        Icon(
            painter = painterResource(id = R.drawable.date_picker_icon),
            modifier = Modifier.align(Alignment.CenterEnd).padding(12.dp),
            contentDescription = null,
            tint = Gray400,
        )
    }

}


