package com.erdemyesilcicek.flokiai.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = darkPink,
    secondary = darkPurple,
    tertiary = darkExtraLightPurple,
    background = darkBackground,
    surface = darkSurface,
    onPrimary = Color.White,
    onBackground = darkVariant,
    onSurface = Color.White,
    onSurfaceVariant = darkVariant,
    onPrimaryContainer = Color(0xFF1F1F1F)
)

private val LightColorScheme = lightColorScheme(
    primary = pink,
    secondary = purple,
    tertiary = extraLightPurple,
    background = offWhite,
    onPrimary = gray,
    onBackground = variant,
    onSurface = onSurface,
    onSurfaceVariant = variant,
    onPrimaryContainer = white
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