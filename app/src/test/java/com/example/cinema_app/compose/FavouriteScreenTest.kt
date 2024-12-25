package com.example.cinema_app.compose

import android.content.res.Resources
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertAll
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.cinema_app.FilmusString
import com.example.cinema_app.presentation.entities.FavouriteMovieUi
import com.example.cinema_app.presentation.state.FavouriteState
import com.example.cinema_app.ui.screen.favorite.FavouriteScreenInternal
import com.example.cinema_app.ui.screen.favorite.testTags.ErrorTestTag
import com.example.cinema_app.ui.screen.favorite.testTags.FavouriteScreenTags
import org.junit.Rule
import org.junit.runner.RunWith
import kotlin.test.Test

/**
 * @author y.yangel
 */
@RunWith(AndroidJUnit4::class)
class FavouriteScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val resources: Resources
        get() = InstrumentationRegistry.getInstrumentation().targetContext.resources

    @Test
    fun testErrorScreen() {
        composeTestRule.launchFinesHistoryScreen(FavouriteState.Error("error"))
        with(composeTestRule) {
            onNodeWithText(ErrorTestTag.ErrorScreenTestTag)
                .isDisplayed()

            onNodeWithContentDescription(resources.getString(FilmusString.wifi_error))
                .isDisplayed()

            onNodeWithTag(ErrorTestTag.ErrorButtonTestTag, useUnmergedTree = true)
                .onChild()
                .assert(hasText(resources.getString(FilmusString.repeat)))
        }
    }

    @Test
    fun testFavouriteScreen() {
        composeTestRule.launchFinesHistoryScreen(FavouriteState.NoFavourite)
        with(composeTestRule) {
            onNodeWithText(FavouriteScreenTags.NoFavouriteScreenTag)
                .onChildren()

            onNodeWithText(resources.getString(FilmusString.no_favourite_text))
                .isDisplayed()

            onNodeWithText(resources.getString(FilmusString.no_favourite_label))
                .isDisplayed()
        }
    }

    @Test
    fun testFavouriteSkeleton() {
        composeTestRule.launchFinesHistoryScreen(FavouriteState.Loading)
        with(composeTestRule) {

            onNodeWithTag(FavouriteScreenTags.FavouriteSkeletonScreenTag, useUnmergedTree = true)
                .onChildren()
                .assertCountEquals(8)
                .assertAll(hasTestTag(FavouriteScreenTags.FavouriteSkeletonCardTag))
        }
    }

    @Test
    fun testContent() {
        composeTestRule.launchFinesHistoryScreen(createContentState())
        with(composeTestRule) {

            with(onNodeWithTag(FavouriteScreenTags.FavouriteLazyColumnTag, useUnmergedTree = true)) {
                onChildAt(0).apply {
                    onNodeWithText("1")
                    onNodeWithText("Веном 3")
                }
                onChildAt(1).apply {
                    onNodeWithText("3")
                    onNodeWithText("Веном 1")
                }
            }
        }
    }

    private fun createContentState(): FavouriteState.Content {
        return FavouriteState.Content(
            movie = listOf(
                FavouriteMovieUi(
                    name = "Веном 3",
                    userReview = 1,
                    id = "id",
                    poster = "poster"
                ),
                FavouriteMovieUi(
                    name = "Веном 1",
                    userReview = 3,
                    id = "id",
                    poster = "poster"
                )
            )
        )
    }

    private fun ComposeContentTestRule.launchFinesHistoryScreen(state: FavouriteState) {
        setContent {
            FavouriteScreenInternal(
                state = state,
                onCardClick = {},
                onRetryClick = {},
                onErrorButtonClick = {}
            )
        }
    }

}

