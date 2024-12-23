package com.erdemyesilcicek.flokiai.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = pink,
    secondary = purple,
    tertiary = extraLightPurple,
    background = offWhite,
    onPrimary = gray,
    onBackground = lightPurple
)

private val LightColorScheme = lightColorScheme(
    primary = pink,
    secondary = purple,
    tertiary = extraLightPurple,
    background = offWhite,
    onPrimary = gray,
    onBackground = lightPurple
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