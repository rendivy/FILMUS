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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cinema_app.R
import com.example.cinema_app.presentation.entities.MovieDetailsUi
import com.example.cinema_app.presentation.MovieDetailsViewModel
import com.example.cinema_app.ui.component.UserRatingBox
import com.example.cinema_app.ui.screen.details.dialog.CustomDropDownMenu
import com.example.cinema_app.ui.screen.details.dialog.EditReviewDialog
import com.example.cinema_app.ui.shimmer.shimmerEffect
import com.example.cinema_app.ui.theme.GenreTitle
import com.example.cinema_app.ui.theme.Gray750
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.padding10
import com.example.cinema_app.ui.theme.mediumPadding
import com.example.cinema_app.ui.theme.padding20
import com.example.cinema_app.ui.theme.shortPadding

@Composable
fun UserReview(content: MovieDetailsUi, viewModel: MovieDetailsViewModel) {
    val reviewDialogOpen = remember { mutableStateOf(false) }

    if (reviewDialogOpen.value) {
        EditReviewDialog(
            dialogIsOpen = reviewDialogOpen,
            movieDetailsViewModel = viewModel,
            movieId = content.id,
            ratingId = content.userReviewApiX!!.id
        )
    }



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
            if (content.userReviewApiX?.author?.avatar != null) {
                UserAvatar(model = content.userReviewApiX.author.avatar)
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
            )
            {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = content.userReviewApiX?.author?.nickName
                                ?: stringResource(id = R.string.anonymous_user),
                            style = GenreTitle,
                            color = Color.White
                        )
                        Text(
                            text = stringResource(id = R.string.my_review),
                            style = GenreTitle,
                            color = Color.Gray,
                        )
                    }
                    Row() {
                        UserRatingBox(userRating = content.userReviewApiX!!.rating)
                        Spacer(modifier = Modifier.width(padding10))
                        Box(
                            modifier = Modifier
                                .size(26.dp)
                                .background(
                                    color = Gray750,
                                    shape = RoundedCornerShape(50.dp)
                                )
                        ) {
                            CustomDropDownMenu(
                                {
                                    viewModel.deleteUserReview(
                                        movieId = content.id,
                                        reviewId = content.userReviewApiX.id
                                    )
                                },
                                {
                                    reviewDialogOpen.value = true
                                }
                            )
                        }

                    }

                }
            }
        }
        Spacer(modifier = Modifier.height(shortPadding))
        Text(
            text = content.userReviewApiX!!.reviewText,
            style = GenreTitle,
            color = Color.White,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(shortPadding))
        Text(
            text = viewModel.convertDate(content.userReviewApiX.createDateTime),
            style = GenreTitle,
            color = Color.Gray,
            modifier = Modifier.fillMaxWidth()
        )

    }
}