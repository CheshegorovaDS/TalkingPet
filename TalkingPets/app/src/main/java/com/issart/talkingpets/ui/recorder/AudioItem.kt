package com.issart.talkingpets.ui.recorder

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.issart.talkingpets.R
import com.issart.talkingpets.ui.common.texts.BodySecondaryText
import com.issart.talkingpets.ui.theme.TextBodySecondaryAudioColor

@Composable
fun AudioItem() {
    var isChecked by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .clickable { isChecked = !isChecked }
    ) {
        Row(
            modifier = Modifier
                .padding(
                    vertical = 8.dp,
                    horizontal = 24.dp
                )
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_play),
                    contentDescription = "play audio image"
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(2f),
                contentAlignment = Alignment.Center
            ) {
                BodySecondaryText(
                    title = "How are you?",
                    color = TextBodySecondaryAudioColor
                )
            }
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterEnd
            ) {
                if(isChecked) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_check),
                        contentDescription = "play audio image"
                    )
                }
            }
        }
    }
}
