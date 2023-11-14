package com.example.cinema_app.ui.screen.details.review

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.example.cinema_app.ui.shimmer.shimmerEffect
import com.example.cinema_app.ui.theme.Gray900

@Composable
fun UserAvatar(model: String) {
    val context = LocalContext.current
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(context).data(model).crossfade(true).build(),
        contentDescription = "user_avatar",
        modifier = Modifier
            .size(40.dp)
            .clip(
                RoundedCornerShape(100.dp)
            )
    )
    {
        when (painter.state) {
            is AsyncImagePainter.State.Loading -> {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(
                            RoundedCornerShape(100.dp)
                        )
                        .background(Gray900)
                        .shimmerEffect()
                )
            }

            is AsyncImagePainter.State.Error -> {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(
                            RoundedCornerShape(100.dp)
                        )
                        .background(Gray900)
                        .shimmerEffect()
                )
            }

            else -> {
                SubcomposeAsyncImageContent()
            }
        }
    }

}