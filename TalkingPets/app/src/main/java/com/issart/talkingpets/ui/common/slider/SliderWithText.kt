package com.issart.talkingpets.ui.common.slider

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.issart.talkingpets.ui.common.texts.BodyMediumText

@Composable
fun SliderWithText(
    value: Float,
    onChangedSlider: (Float) -> Unit,
    text: String
) = Row(
    modifier = Modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically
) {
    Box(modifier = Modifier.weight(DEFAULT_WEIGHT_TEXT)) {
        BodyMediumText(title = text)
    }
    Box(modifier = Modifier.weight(DEFAULT_WEIGHT_SLIDER)) {
        AudioSlider(
            value = value,
            onChangedSlider = { onChangedSlider(it) }
        )
    }
}

const val DEFAULT_WEIGHT_TEXT = 1f
const val DEFAULT_WEIGHT_SLIDER = 3f