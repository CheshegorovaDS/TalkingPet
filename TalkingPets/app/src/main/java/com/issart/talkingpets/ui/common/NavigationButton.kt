package com.issart.talkingpets.ui.common

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.issart.talkingpets.ui.theme.TextTitleColor
import com.issart.talkingpets.ui.theme.White
import com.issart.talkingpets.ui.utils.onClickCallback

@Composable
fun NavigationButton(
    isFocused: Boolean,
    background: Color,
    iconId: Int,
    onClick: onClickCallback,
    indexCurrentButton: Int,
    iconIsFocusedColor: Color = COLOR_FOCUSED_ICON,
    iconIsUnfocusedColor: Color = COLOR_UNFOCUSED_ICON,
    contentDescription: String = MENU_BUTTON_DESCRIPTION,
    countMenuButtons: Int = COUNT_MENU_BUTTONS,
) {
    val configuration = LocalConfiguration.current
    val height = getHeight(isFocused).dp
    val width = (configuration.screenWidthDp * indexCurrentButton / countMenuButtons).dp

    Button(
        modifier = Modifier
            .size(width, height)
            .animateContentSize(),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(backgroundColor = background),
        shape = RoundedCornerShape(0, TOP_END_PERCENT,0,0),
        elevation = ButtonDefaults.elevation(DEFAULT_ELEVATION.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = iconId),
                contentDescription = contentDescription,
                colorFilter = ColorFilter.tint(
                    color = if (isFocused) {
                        iconIsFocusedColor
                    } else {
                        iconIsUnfocusedColor
                    }
                )
            )
        }
    }
}

private fun getHeight(isFocused: Boolean) = if (isFocused) {
    HEIGHT_FOCUSED_BUTTON
} else {
    HEIGHT_UNFOCUSED_BUTTON
}

const val TOP_END_PERCENT = 40
const val DEFAULT_ELEVATION = 4
const val HEIGHT_FOCUSED_BUTTON = 80
const val HEIGHT_UNFOCUSED_BUTTON = 72
const val MENU_BUTTON_DESCRIPTION = "Navigation menu button."

private val COLOR_FOCUSED_ICON = White
private val COLOR_UNFOCUSED_ICON = TextTitleColor
private const val COUNT_MENU_BUTTONS = 5
