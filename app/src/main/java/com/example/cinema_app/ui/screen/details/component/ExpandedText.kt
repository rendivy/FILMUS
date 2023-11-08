package com.example.cinema_app.ui.screen.details.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import com.example.cinema_app.ui.theme.Accent
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.TitleMedium
import com.example.cinema_app.ui.theme.padding8

@Composable
fun ExpandedText(
    text: String,
    color: Color,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    style: TextStyle = TitleMedium,
    fontStyle: FontStyle? = null,
    collapsedMaxLine: Int = 2,
    textAlign: TextAlign? = null,
) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    var clickable by remember { mutableStateOf(false) }
    var lastCharIndex by remember { mutableIntStateOf(0) }
    var containerSize by remember { mutableStateOf(IntSize.Zero) }

    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Gray900), contentAlignment = Alignment.Center){


        Column(
            modifier = modifier.background(Gray900)
        ) {
            Box(modifier = Modifier
                .onGloballyPositioned {
                    containerSize = it.size
                }
            ) {
                Text(
                    modifier = textModifier
                        .fillMaxWidth()
                        .animateContentSize(),
                    text = buildAnnotatedString {
                        if (clickable) {
                            if (isExpanded) {
                                append(text)
                            } else {
                                val adjustText =
                                    text.substring(startIndex = 0, endIndex = lastCharIndex)
                                        .dropLastWhile { Character.isWhitespace(it) || it == '.' }
                                append(adjustText)
                            }
                        } else {
                            append(text)
                        }
                    },
                    maxLines = if (isExpanded) Int.MAX_VALUE else collapsedMaxLine,
                    fontStyle = fontStyle,
                    onTextLayout = { textLayoutResult ->
                        if (!isExpanded && textLayoutResult.hasVisualOverflow) {
                            clickable = true
                            lastCharIndex = textLayoutResult.getLineEnd(collapsedMaxLine - 1)
                        }
                    },
                    style = style,
                    textAlign = textAlign,
                    color = color
                )
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .then(
                            if (isExpanded) {
                                Modifier
                            } else {
                                Modifier.background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            Gray900,
                                        ),
                                        startY = 0f,
                                        endY = containerSize.height.toFloat()
                                    )
                                )
                            }
                        )
                )
            }

            Row(
                modifier = Modifier
                    .background(Gray900)
                    .clickable {
                        isExpanded = !isExpanded
                    },
            ) {
                Text(
                    text = if (!isExpanded) {
                        "Показать еще"
                    } else {
                        "Скрыть"
                    },
                    style = TitleMedium,
                    color = Accent
                )

                Spacer(modifier = Modifier.width(padding8))

                Icon(
                    imageVector = if (!isExpanded) {
                        Icons.Filled.KeyboardArrowDown
                    } else {
                        Icons.Filled.KeyboardArrowUp
                    },
                    contentDescription = null,
                    tint = Accent
                )
            }
        }
    }
}