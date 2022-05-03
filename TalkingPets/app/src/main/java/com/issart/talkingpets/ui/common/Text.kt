package com.issart.talkingpets.ui.common

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.issart.talkingpets.ui.theme.TextTitleColor

@Composable
fun Title(title: String) {
    Text(
        text = title,
        color = TextTitleColor,
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight(500)
    )
}
