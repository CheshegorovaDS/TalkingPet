package com.issart.talkingpets.ui.common.slider

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.issart.talkingpets.ui.theme.SliderTrackColor
import com.issart.talkingpets.ui.theme.TextTitleColor

@Composable
fun AudioSlider(
    modifier: Modifier = Modifier,
    value: Float,
    onChangedSlider: (Float) -> Unit
) = Slider(
    modifier = modifier.padding(start = DEFAULT_START_PADDING.dp),
    value = value,
    onValueChange = { onChangedSlider(it) },
    valueRange = 0f..10f,
    colors = SliderDefaults.colors(
        thumbColor = TextTitleColor,
        activeTrackColor = SliderTrackColor,
        inactiveTrackColor = SliderTrackColor
    )
)

const val DEFAULT_START_PADDING = 4
