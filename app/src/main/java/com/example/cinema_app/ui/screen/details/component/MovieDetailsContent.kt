package com.example.cinema_app.ui.screen.details.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.cinema_app.R
import com.example.cinema_app.data.converter.BudgetConverter
import com.example.cinema_app.data.entity.GenreX
import com.example.cinema_app.data.entity.ReviewX
import com.example.cinema_app.domain.entity.DetailsDTO
import com.example.cinema_app.presentation.MovieDetailsViewModel
import com.example.cinema_app.ui.component.UserRatingBox
import com.example.cinema_app.ui.screen.details.dialog.MyUI
import com.example.cinema_app.ui.screen.details.dialog.ReviewDialog
import com.example.cinema_app.ui.screen.home.GenreTag
import com.example.cinema_app.ui.screen.home.RatingBox
import com.example.cinema_app.ui.shimmer.shimmerEffect
import com.example.cinema_app.ui.theme.Accent
import com.example.cinema_app.ui.theme.GenreTitle
import com.example.cinema_app.ui.theme.Gray750
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.InternBoldLarge
import com.example.cinema_app.ui.theme.SemiBoldStyle
import com.example.cinema_app.ui.theme.padding16

@ExperimentalLayoutApi
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun MovieDetailsContent(
    content: DetailsDTO,
    filmRating: String,
    movieDetailsViewModel: MovieDetailsViewModel
) {
    val lazyListState = rememberLazyListState()
    val reviewDialogOpen = remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    var scrolledY = 0f
    var previousOffset = 0
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(44.dp),
                title = {},
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Gray900,
                    scrolledContainerColor = Gray900,
                    navigationIconContentColor = Color.White,
                    titleContentColor = Accent
                ),
                navigationIcon = {
                    IconButton(onClick = {
                    }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.back_button_icon),
                            contentDescription = "back_icon_button",
                            modifier = Modifier.size(12.dp),
                            tint = Color.White,
                        )
                    }
                },
            )
        },
        content = {
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(color = Gray900),
                lazyListState,
            ) {
                item {
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
                item {
                    Row(
                        modifier = Modifier
                            .background(color = Gray900)
                            .padding(
                                start = padding16,
                                end = padding16,
                                top = padding16,
                                bottom = 20.dp
                            )
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        RatingBox(filmRating)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = content.name,
                            modifier = Modifier
                                .weight(1f)
                                .background(color = Gray900),
                            style = InternBoldLarge,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .background(
                                    color = Gray750,
                                    shape = RoundedCornerShape(50.dp)
                                )
                                .width(40.dp)
                                .height(40.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.favourite_icon),
                                contentDescription = null
                            )
                        }

                    }

                }
                if (content.description != "-") {
                    item {
                        ExpandedText(
                            text = content.description,
                            color = Color.White,
                            modifier = Modifier.padding(
                                start = padding16,
                                end = padding16,
                                bottom = 10.dp,
                                top = padding16
                            )
                        )
                    }
                }
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Gray900),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Жанры",
                            modifier = Modifier
                                .background(color = Gray900)
                                .padding(start = padding16, end = padding16, bottom = 10.dp),
                            style = SemiBoldStyle
                        )
                        GenreSection(content.genres)
                        Spacer(
                            modifier = Modifier
                                .height(10.dp)
                                .fillMaxWidth()
                                .background(Gray900)
                        )
                        AboutFilmSection(content)
                    }
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = padding16),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Отзывы",
                            modifier = Modifier
                                .background(color = Gray900)
                                .padding(start = padding16, end = padding16, bottom = padding16),
                            style = SemiBoldStyle
                        )
                        if (content.userReviewX == null) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .background(color = Accent, shape = RoundedCornerShape(50.dp))
                                    .clip(RoundedCornerShape(50.dp))
                            ) {
                                IconButton(onClick = {
                                    reviewDialogOpen.value = true
                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.add_film_icon),
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    }
                    if (reviewDialogOpen.value) {
                        ReviewDialog(reviewDialogOpen, movieDetailsViewModel, movieId = content.id)
                    }

                    ReviewSection(content = content, viewModel = movieDetailsViewModel)
                }
            }

        })
}


@ExperimentalLayoutApi
@Composable
fun GenreSection(data: List<GenreX>) {
    FlowRow(
        modifier = Modifier
            .padding(start = 12.dp)
            .fillMaxWidth()
            .background(Gray900)
    ) {
        data.forEach {
            GenreTag(it.name, Accent, tinyPadding = 5.dp, mediumPadding = 10.dp)
        }
    }

}


@Composable
fun AboutFilmSection(content: DetailsDTO) {
    Text(
        text = "О фильме",
        modifier = Modifier
            .background(color = Gray900)
            .padding(start = padding16, end = padding16, bottom = 10.dp),
        style = SemiBoldStyle
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = padding16, end = padding16, bottom = 20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        AboutItem(title = "Год", value = content.year.toString())
        Spacer(modifier = Modifier.height(10.dp))
        AboutItem(title = "Cтрана", value = content.country)
        Spacer(modifier = Modifier.height(10.dp))
        AboutItem(title = "Cлоган", value = content.tagline)
        Spacer(modifier = Modifier.height(10.dp))
        AboutItem(title = "Режиссёр", value = content.director)
        if (content.budget != null) {
            Spacer(modifier = Modifier.height(10.dp))
            AboutItem(title = "Бюджет", value = BudgetConverter.convertBudget(content.budget))
        }
        if (content.fees != null) {
            Spacer(modifier = Modifier.height(10.dp))
            AboutItem(title = "Cборы в мире", value = BudgetConverter.convertBudget(content.fees))
        }
        Spacer(modifier = Modifier.height(10.dp))
        AboutItem(title = "Возраст", value = "${content.ageLimit}+")
        Spacer(modifier = Modifier.height(10.dp))
        AboutItem(title = "Время", value = "${content.time} мин")
    }
}


@Composable
fun ReviewSection(content: DetailsDTO, viewModel: MovieDetailsViewModel) {
    if (content.userReviewX != null) {
        UserReview(content, viewModel)
    }
    content.reviews.forEach {
        if (it.isAnonymous) {
            AnonymousCard(it, viewModel = viewModel)
        } else {
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
            .padding(start = padding16, end = padding16, bottom = 20.dp)
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
                    .padding(start = 10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = CenterVertically
                ) {
                    Text(
                        text = review.author?.nickName ?: "Анонимный пользователь",
                        style = GenreTitle,
                        color = Color.White
                    )
                    UserRatingBox(userRating = review.rating)
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = review.reviewText,
            style = GenreTitle,
            color = Color.White,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = viewModel.convertDate(review.createDateTime),
            style = GenreTitle,
            color = Color.Gray,
            modifier = Modifier.fillMaxWidth()
        )

    }
}

@Composable
fun AnonymousCard(review: ReviewX, viewModel: MovieDetailsViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = padding16, end = padding16, bottom = 20.dp)
            .background(Gray900)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(
                        RoundedCornerShape(100.dp)
                    )
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Image(painterResource(id = R.drawable.profile_icon), contentDescription = null)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Анонимный пользователь",
                        style = GenreTitle,
                        color = Color.White
                    )
                    UserRatingBox(userRating = review.rating)
                }

            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = review.reviewText,
            style = GenreTitle,
            color = Color.White,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = viewModel.convertDate(review.createDateTime),
            style = GenreTitle,
            color = Color.Gray,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Composable
fun UserAvatar(model: String) {
    SubcomposeAsyncImage(
        model = model, contentDescription = "user_avatar", modifier = Modifier
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


@Composable
fun UserReview(content: DetailsDTO, viewModel: MovieDetailsViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = padding16, end = padding16, bottom = 20.dp)
            .background(Gray900)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (content.userReviewX?.author?.avatar != null) {
                UserAvatar(model = content.userReviewX.author.avatar)
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
                    .padding(start = 10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = content.userReviewX?.author?.nickName
                                ?: "Анонимный пользователь",
                            style = GenreTitle,
                            color = Color.White
                        )
                        Text(
                            text = "мой отзыв",
                            style = GenreTitle,
                            color = Color.Gray,
                        )
                    }
                    Row() {
                        UserRatingBox(userRating = content.userReviewX!!.rating)
                        Spacer(modifier = Modifier.width(10.dp))
                        Box(
                            modifier = Modifier
                                .size(26.dp)
                                .background(
                                    color = Gray750,
                                    shape = RoundedCornerShape(50.dp)
                                )
                        ) {
                            MyUI()
                        }

                    }

                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = content.userReviewX!!.reviewText,
            style = GenreTitle,
            color = Color.White,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = viewModel.convertDate(content.userReviewX.createDateTime),
            style = GenreTitle,
            color = Color.Gray,
            modifier = Modifier.fillMaxWidth()
        )

    }
}


@Composable
fun AboutItem(title: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = title,
            style = GenreTitle,
            modifier = Modifier.width(100.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = value,
            style = GenreTitle,
            color = Color.White,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}


