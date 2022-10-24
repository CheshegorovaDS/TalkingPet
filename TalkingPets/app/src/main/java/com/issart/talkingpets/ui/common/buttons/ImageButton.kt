package com.issart.talkingpets.ui.common.buttons

import android.view.MotionEvent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageButton(
    modifier: Modifier = Modifier,
    size: Dp,
    onClickLabel: String = "Clickable image",
    onClick: () -> Unit = {},
    onLongClick: () -> Unit = {},
    imageId: Int,
    contentDescription: String = "Image Button.",
    colorFilter: ColorFilter? = null
) {
    Image(
        modifier = modifier
            .size(size)
            .combinedClickable(
                enabled = true,
                onClickLabel = onClickLabel,
                onClick = { onClick() },
                onLongClick = { onLongClick() }
            ),
        painter = painterResource(id = imageId),
        contentDescription = contentDescription,
        colorFilter = colorFilter
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ActionImageButton(
    modifier: Modifier = Modifier,
    size: Dp,
    onActionUpEvent: () -> Unit,
    onActionDownEvent: () -> Unit,
    imageId: Int,
    contentDescription: String = "Image Button.",
    colorFilter: ColorFilter? = null
) {
    Image(
        modifier = modifier
            .size(size)
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> onActionDownEvent()
                    MotionEvent.ACTION_UP -> onActionUpEvent()
                }
                true
            },
        painter = painterResource(id = imageId),
        contentDescription = contentDescription,
        colorFilter = colorFilter
    )
}
