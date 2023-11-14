package com.example.cinema_app.ui.screen.details.review

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cinema_app.R
import com.example.cinema_app.data.entity.ReviewX
import com.example.cinema_app.domain.entity.DetailsDTO
import com.example.cinema_app.presentation.MovieDetailsViewModel
import com.example.cinema_app.ui.component.UserRatingBox
import com.example.cinema_app.ui.shimmer.shimmerEffect
import com.example.cinema_app.ui.theme.GenreTitle
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.padding10
import com.example.cinema_app.ui.theme.mediumPadding
import com.example.cinema_app.ui.theme.padding20
import com.example.cinema_app.ui.theme.shortPadding

@Composable
fun ReviewSection(content: DetailsDTO, viewModel: MovieDetailsViewModel) {
    if (content.userReviewX != null) {
        UserReview(content, viewModel)
    }
    content.reviews.forEach {
        if (it.isAnonymous) {
            AnonymousCard(it, viewModel = viewModel)
        }
        else {
            if (it.author != null) {
                ReviewItem(it, viewModel = viewModel)
            }

        }
    }
}


@Composable
fun ReviewItem(review: ReviewX, viewModel: MovieDetailsViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = mediumPadding, end = mediumPadding, bottom = padding20)
            .background(Gray900)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (review.author?.avatar != null) {
                UserAvatar(model = review.author.avatar)
            } else {
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

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = padding10)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = review.author?.nickName ?: stringResource(id = R.string.anonymous_user),
                        style = GenreTitle,
                        color = Color.White
                    )
                    UserRatingBox(userRating = review.rating)
                }
            }
        }
        Spacer(modifier = Modifier.height(shortPadding))
        Text(
            text = review.reviewText,
            style = GenreTitle,
            color = Color.White,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(shortPadding))
        Text(
            text = viewModel.convertDate(review.createDateTime),
            style = GenreTitle,
            color = Color.Gray,
            modifier = Modifier.fillMaxWidth()
        )

    }
}