package com.issart.talkingpets.ui.detector.detectorPoints

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun DetectorBox() {
    val configuration = LocalConfiguration.current
    val boxSize = configuration.screenWidthDp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(boxSize.dp)
    ) {
        DetectorEyes()
//        DetectorFace()
    }
}
