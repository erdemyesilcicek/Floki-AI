package com.erdemyesilcicek.flokiai.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = pink,
    secondary = purple,
    tertiary = extraLightPurple,
    background = offWhite,
    onPrimary = gray,
    onBackground = Color(red = 73, green = 69, blue = 79),
    onSurface = Color(red = 28, green = 27, blue = 31),
    onSurfaceVariant = Color(red = 73, green = 69, blue = 79)
)

private val LightColorScheme = lightColorScheme(
    primary = pink,
    secondary = purple,
    tertiary = extraLightPurple,
    background = offWhite,
    onPrimary = gray,
    onBackground = Color(red = 73, green = 69, blue = 79),
    onSurface = Color(red = 28, green = 27, blue = 31),
    onSurfaceVariant = Color(red = 73, green = 69, blue = 79)
)

@Composable
fun FlokiAITheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}