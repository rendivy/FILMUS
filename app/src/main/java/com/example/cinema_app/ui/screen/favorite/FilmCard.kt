package com.example.cinema_app.ui.screen.favorite

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.cinema_app.R
import com.example.cinema_app.ui.screen.favorite.testTags.FavouriteScreenTags
import com.example.cinema_app.ui.shimmer.shimmerEffect
import com.example.cinema_app.ui.theme.BadRatingColor
import com.example.cinema_app.ui.theme.GoodRatingColor
import com.example.cinema_app.ui.theme.MediumRatingColor
import com.example.cinema_app.ui.theme.SemiBadRatingColor
import com.example.cinema_app.ui.theme.SemiGoodRatingColor
import com.example.cinema_app.ui.theme.SemiMediumRatingColor
import com.example.cinema_app.ui.theme.TitleMedium
import com.example.cinema_app.ui.theme.mediumPadding
import com.example.cinema_app.ui.theme.padding5
import com.example.cinema_app.ui.theme.tinyPadding

@Composable
fun FilmCard(
    path: String,
    movieId: String,
    movieName: String,
    onCardClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    userRating: Int? = null,
) {
    Column(modifier = modifier.testTag(FavouriteScreenTags.FavouriteCardTag)) {
        FilmImage(path, movieId, onCardClick, userRating)
        Spacer(modifier = Modifier.height(padding5))
        FilmTitle(movieName)
    }
}

@Preview(showBackground = true)
@Composable
fun FilmCardPreview() {
    FilmCard(
        path = "https://example.com/movie_poster.jpg",
        movieId = "12345",
        movieName = "Sample Movie Title",
        onCardClick = { movieId -> /* Handle card click */ },
        userRating = 8,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun FilmImage(
    path: String,
    movieId: String,
    onCardClick: (String) -> Unit,
    userRating: Int?
) {
    Box(contentAlignment = Alignment.TopEnd) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .height(240.dp)
                .clip(RoundedCornerShape(5.dp))
                .clickable { onCardClick(movieId) }
                .testTag(FavouriteScreenTags.FavouriteCardImageTag),
            model = path,
            contentScale = ContentScale.Crop,
            contentDescription = "Film Card"
        ) {
            when (painter.state) {
                is AsyncImagePainter.State.Loading -> LoadingPlaceholder()
                else -> SubcomposeAsyncImageContent()
            }
        }
        userRating?.let { RatingBadge(it) }
    }
}

@Composable
private fun LoadingPlaceholder() {
    Box(
        modifier = Modifier
            .height(240.dp)
            .width(200.dp)
            .clip(RoundedCornerShape(5.dp))
            .shimmerEffect()
    )
}

@Composable
private fun RatingBadge(userRating: Int) {
    val color = getRatingColor(userRating)
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(35.dp))
            .background(color)
            .padding(2.dp)
            .testTag(FavouriteScreenTags.FavouriteCardRatingBadge),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            StarIcon()
            UserRatingText(userRating)
        }
    }
}

@Composable
private fun StarIcon() {
    Image(
        painter = painterResource(id = R.drawable.star_3),
        contentDescription = "star rating",
        modifier = Modifier
            .padding(tinyPadding)
            .size(
                mediumPadding
            )
    )
}

@Composable
private fun UserRatingText(userRating: Int) {
    Text(
        text = "$userRating",
        style = TitleMedium,
        color = Color.White,
        fontSize = 16.sp,
        modifier = Modifier.padding(end = tinyPadding)
    )
}

@Composable
private fun FilmTitle(movieName: String, modifier: Modifier = Modifier) {
    Text(
        text = movieName,
        style = TitleMedium,
        fontSize = 14.sp,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier.testTag(FavouriteScreenTags.FavouriteCardTitleTag)
    )
}

@Composable
private fun getRatingColor(userRating: Int): Color {
    return when (userRating) {
        in 0..2 -> BadRatingColor
        in 2..4 -> SemiBadRatingColor
        in 4..5 -> SemiMediumRatingColor
        in 6 until 7 -> MediumRatingColor
        in 7..8 -> SemiGoodRatingColor
        in 9..10 -> GoodRatingColor
        else -> Color.White
    }
}
