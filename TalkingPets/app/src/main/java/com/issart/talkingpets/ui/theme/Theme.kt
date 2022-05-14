package com.issart.talkingpets.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200,
    onBackground = Color.Black
)

private val LightColorPalette = lightColors(
    primary = BackgroundColor,
    primaryVariant = BackgroundColor,
    secondary = BackgroundColor,
    background = BackgroundColor,
    surface = BackgroundColor,
    secondaryVariant = BackgroundColor
)

@Composable
fun TalkingPetsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    rememberSystemUiController().setStatusBarColor(colors.secondary)
    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}