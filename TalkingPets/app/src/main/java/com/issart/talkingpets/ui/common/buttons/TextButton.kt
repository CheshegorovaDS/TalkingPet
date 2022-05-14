package com.issart.talkingpets.ui.common.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.issart.talkingpets.ui.common.texts.ButtonsText

@Composable
fun TextButtonsWithImage(
    modifier: Modifier = Modifier,
    icon: Painter,
    text: String,
    backgroundColor: Color,
    sizeIcon: Dp,
    onClick: () -> Unit
) = Button(
    modifier = modifier,
    onClick = { onClick() },
    colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
    shape = RoundedCornerShape(PERCENT_ROUNDED_CORNER_BUTTON),
    elevation = ButtonDefaults.elevation(defaultElevation = DEFAULT_ELEVATION.dp)
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(sizeIcon),
            painter = icon,
            contentDescription = text
        )
        ButtonsText(title = text)
    }
}

@Composable
fun TextButton(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColor: Color,
    onClick: () -> Unit
) = Button(
    modifier = modifier,
    onClick = { onClick() },
    colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
    shape = RoundedCornerShape(PERCENT_ROUNDED_CORNER_BUTTON),
    elevation = ButtonDefaults.elevation(defaultElevation = DEFAULT_ELEVATION.dp)
) {
    ButtonsText(title = text)
}

const val PERCENT_ROUNDED_CORNER_BUTTON = 25
const val PADDING_START_TEXT = 8
