package com.example.cinema_app.ui.screen.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cinema_app.ui.screen.favorite.testTags.FavouriteScreenTags
import com.example.cinema_app.ui.shimmer.shimmerEffect
import com.example.cinema_app.ui.theme.mediumPadding
import com.example.cinema_app.ui.theme.padding15
import com.example.cinema_app.ui.theme.padding20
import com.example.cinema_app.ui.theme.padding5

@Composable
fun FavouriteScreenSkeleton(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(top = mediumPadding)
            .testTag(FavouriteScreenTags.FavouriteSkeletonScreenTag)
    ) {
        for (j in 0..3) {
            Row(
                modifier = Modifier
                    .padding(
                        start = padding15,
                        top = padding20,
                        bottom = padding20,
                        end = padding15
                    ),
            ) {
                repeat(3) { index ->
                    if (index == 1) {
                        Spacer(modifier = Modifier.width(15.dp))
                    }
                    if (index < 2) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(240.dp)
                                .clip(RoundedCornerShape(padding5))
                                .shimmerEffect()
                                .testTag(FavouriteScreenTags.FavouriteSkeletonCardTag),
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavouriteScreenSkeletonPreview() {
    FavouriteScreenSkeleton()
}
