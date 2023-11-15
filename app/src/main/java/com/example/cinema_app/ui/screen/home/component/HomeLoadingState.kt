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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinema_app.R
import com.example.cinema_app.ui.shimmer.shimmerEffect
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.SemiBoldStyle
import com.example.cinema_app.ui.theme.padding10
import com.example.cinema_app.ui.theme.mediumPadding

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
            modifier = Modifier.padding(top = mediumPadding, start = mediumPadding),
            text = stringResource(id = R.string.magazine_label),
            textAlign = TextAlign.Start,
            style = SemiBoldStyle,
            fontSize = 24.sp,
        )
        repeat(2) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = mediumPadding, bottom = mediumPadding)
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
                        .padding(start = padding10)
                        .shimmerEffect()
                )
            }
        }
    }
}
