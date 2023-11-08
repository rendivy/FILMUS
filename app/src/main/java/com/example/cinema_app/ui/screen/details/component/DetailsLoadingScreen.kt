package com.example.cinema_app.ui.screen.details.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cinema_app.ui.shimmer.shimmerEffect
import com.example.cinema_app.ui.theme.Gray900

@Composable
fun DetailsLoadingScreen() {
    Box(
        modifier = Modifier
            .height(500.dp)
            .fillMaxWidth()
            .background(color = Gray900)
            .shimmerEffect(),
    )
}
