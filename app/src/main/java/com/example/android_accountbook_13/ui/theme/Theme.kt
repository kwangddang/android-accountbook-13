package com.example.android_accountbook_13.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = OffWhite,
    primaryVariant = OffWhite,
    secondary = Purple,
    background = OffWhite,
    surface = OffWhite,
    onPrimary = Purple,
    onSecondary = White,
    onBackground = Purple,
    onSurface = Purple,
)

private val LightColorPalette = lightColors(
    primary = OffWhite,
    primaryVariant = OffWhite,
    secondary = Purple,
    background = OffWhite,
    surface = OffWhite,
    onPrimary = Purple,
    onSecondary = White,
    onBackground = Purple,
    onSurface = Purple,
)

@Composable
fun AccountBookTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}