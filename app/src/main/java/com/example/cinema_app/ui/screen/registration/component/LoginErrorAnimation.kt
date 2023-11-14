package com.example.cinema_app.ui.screen.registration.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cinema_app.ui.theme.Red
import com.example.cinema_app.ui.theme.TitleMedium
import com.example.cinema_app.ui.theme.shortPadding

@Composable
fun LoginErrorAnimation(errorMessage: String) {
    Box(contentAlignment = Alignment.Center) {
        AnimatedVisibility(visible = true, modifier = Modifier.padding(top = shortPadding)) {
            Text(
                text = errorMessage,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                color = Red,
                style = TitleMedium,
                textAlign = TextAlign.Start
            )
        }
    }


}