package com.example.cinema_app.ui.component.switcher

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import com.example.cinema_app.ui.theme.Black300
import com.example.cinema_app.ui.theme.Gray400
import com.example.cinema_app.ui.theme.TitleSmall
import com.example.cinema_app.ui.theme.padding8

fun ContentDrawScope.drawWithLayer(block: ContentDrawScope.() -> Unit) {
    with(drawContext.canvas.nativeCanvas) {
        val checkPoint = saveLayer(null, null)
        block()
        restoreToCount(checkPoint)
    }
}

@Composable
fun TextSwitch(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    items: List<String>,
    onSelectionChange: (Int) -> Unit
) {

    BoxWithConstraints(
        modifier
            .height(42.dp)
            .clip(RoundedCornerShape(padding8))
            .background(
                color = Black300
            )
            .fillMaxWidth().padding(2.dp)
    ) {
        if (items.isNotEmpty()) {

            val maxWidth = this.maxWidth
            val tabWidth = maxWidth / items.size

            val indicatorOffset by animateDpAsState(
                targetValue = tabWidth * selectedIndex,
                animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing),
                label = "indicator offset"
            )
            Box(
                modifier = Modifier
                    .offset(x = indicatorOffset)
                    .shadow(4.dp, RoundedCornerShape(padding8))
                    .width(tabWidth)
                    .fillMaxHeight()
            )

            Row(modifier = Modifier
                .fillMaxWidth()

                .drawWithContent {
                    val padding = padding8.toPx()
                    drawRoundRect(
                        topLeft = Offset(x = indicatorOffset.toPx() + padding, padding),
                        size = Size(size.width / 2 - padding * 2, size.height - padding * 2),
                        color = Color.Black,
                        cornerRadius = CornerRadius(x = padding8.toPx(), y = padding8.toPx()),
                    )

                    drawWithLayer {
                        drawContent()
                        drawRoundRect(
                            topLeft = Offset(x = indicatorOffset.toPx(), 0f),
                            size = Size(size.width / 2, size.height),
                            color = Color.White,
                            cornerRadius = CornerRadius(x = padding8.toPx(), y = padding8.toPx()),
                            blendMode = BlendMode.SrcOut
                        )
                    }

                }
            ) {
                items.forEachIndexed { index, text ->
                    Box(
                        modifier = Modifier
                            .width(tabWidth)
                            .fillMaxHeight()
                            .clickable(
                                interactionSource = remember {
                                    MutableInteractionSource()
                                },
                                indication = null,
                                onClick = {
                                    onSelectionChange(index)
                                }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = text,
                            style = TitleSmall,
                            color = Gray400
                        )
                    }
                }
            }
        }
    }
}