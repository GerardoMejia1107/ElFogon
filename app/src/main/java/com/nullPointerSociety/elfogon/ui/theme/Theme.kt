package com.nullPointerSociety.elfogon.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = PastelGreenLight,
    onPrimary = PrimaryText,
    secondary = PrimaryAccent,
    onSecondary = Color.White,
    background = Color(0xFF121212),
    onBackground = Color.White,
    surface = Color(0xFF1E1E1E),
    onSurface = Color.White,
    surfaceVariant = Color(0xFF2C2C2C)
)


private val LightColorScheme = lightColorScheme(
    primary = PrimaryAccent,
    onPrimary = Color.White,
    secondary = PastelGreenLight,
    onSecondary = PrimaryText,
    background = AppBackground,
    onBackground = PrimaryText,
    surface = CardBackground,
    onSurface = PrimaryText,
    surfaceVariant = SurfaceSecondary
)


@Composable
fun ElFogonTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}


object AppColors {

    // Botones
    val primaryButtonBg: Color
        @Composable get() = MaterialTheme.colorScheme.primary

    val primaryButtonText: Color
        @Composable get() = MaterialTheme.colorScheme.secondary

    // Fondos
    val cardBackground: Color
        @Composable get() = MaterialTheme.colorScheme.surface

    val appBackground: Color
        @Composable get() = MaterialTheme.colorScheme.background

    // Textos
    val titleText: Color
        @Composable get() = MaterialTheme.colorScheme.primary

    val bodyText: Color
        @Composable get() = MaterialTheme.colorScheme.onBackground


}
