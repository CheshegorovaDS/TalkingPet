package com.issart.talkingpets.ui.common.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp

@Composable
fun ImageButton(
    modifier: Modifier = Modifier,
    size: Dp,
    onClickLabel: String = "Clickable image",
    onClick: () -> Unit,
    imageId: Int,
    contentDescription: String = "Image Button.",
    colorFilter: ColorFilter? = null
) {
    Image(
        modifier = modifier
            .size(size)
            .clickable(
                enabled = true,
                onClickLabel = onClickLabel,
                onClick = { onClick() }
            ),
        painter = painterResource(id = imageId),
        contentDescription = contentDescription,
        colorFilter = colorFilter
    )
}
