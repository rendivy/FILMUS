package com.example.cinema_app.ui.screen.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cinema_app.domain.entity.DetailsDTO
import com.example.cinema_app.ui.theme.Gray900

@Composable
fun DetailsPoster(content: DetailsDTO, lazyListState: LazyListState) {
    var scrolledY = 0f
    var previousOffset = 0
    Box {
        AsyncImage(
            model = content.poster,
            contentDescription = null,
            modifier = Modifier
                .graphicsLayer {
                    scrolledY += lazyListState.firstVisibleItemScrollOffset - previousOffset
                    translationY = scrolledY * 0.5f
                    previousOffset = lazyListState.firstVisibleItemScrollOffset
                }
                .height(500.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .height(500.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Gray900,
                        ),
                        startY = 0f,
                        endY = 1900f
                    )
                )
        )

    }
}