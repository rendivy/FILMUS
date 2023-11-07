package com.example.cinema_app.ui.screen.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinema_app.ui.shimmer.shimmerEffect
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.SemiBoldStyle

@Composable
fun HomeLoadingState(cardHeight: Dp) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray900)
            .verticalScroll(rememberScrollState()),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(cardHeight)
                .shimmerEffect(),
        )
        Text(
            modifier = Modifier.padding(top = 16.dp, start = 16.dp),
            text = "Каталог",
            textAlign = TextAlign.Start,
            style = SemiBoldStyle,
            fontSize = 24.sp,
        )
        repeat(2) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, bottom = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .height(130.dp)
                        .width(95.dp)
                        .shimmerEffect(),
                )
                Box(
                    modifier = Modifier
                        .height(130.dp)
                        .fillMaxWidth()
                        .padding(start = 10.dp)
                        .shimmerEffect()
                )
            }
        }
    }
}
