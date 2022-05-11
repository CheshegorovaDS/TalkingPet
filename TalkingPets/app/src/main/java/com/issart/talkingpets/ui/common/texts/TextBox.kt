package com.issart.talkingpets.ui.common.texts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.issart.talkingpets.R

@Composable
fun BodyText(title: String) = Box(
    modifier = Modifier
        .fillMaxWidth()
        .padding(
            top = 32.dp,
            start = 16.dp,
            end = 16.dp
        ),
    contentAlignment = Alignment.TopCenter
) {
    BodyMediumText(title = title)
}

@Composable
fun TitleText(title: String) = Box(
    modifier = Modifier
        .fillMaxWidth()
        .padding(
            top = 32.dp,
            start = 16.dp,
            end = 16.dp
        ),
    contentAlignment = Alignment.TopCenter
) {
    TitleBoldText(title = title)
}
