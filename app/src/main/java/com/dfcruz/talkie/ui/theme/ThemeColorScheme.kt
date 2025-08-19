package com.dfcruz.talkie.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ThemeColorScheme(
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,

    val secondary: Color,
    val onSecondary: Color,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,

    val tertiary: Color,
    val onTertiary: Color,
    val tertiaryContainer: Color,
    val onTertiaryContainer: Color,

    val error: Color,
    val onError: Color,
    val errorContainer: Color,
    val onErrorContainer: Color,

    val surfaceDim: Color,
    val surface: Color,
    val surfaceBright: Color,
    val onSurface: Color,
    val onSurfaceVariant: Color,

    val surfaceContainerLowest: Color,
    val surfaceContainerLow: Color,
    val surfaceContainer: Color,
    val surfaceContainerHigh: Color,
    val surfaceContainerHighest: Color,

    val inverseSurface: Color,
    val inverseOnSurface: Color,
    val inversePrimary: Color,

    val outline: Color,
    val outlineVariant: Color,

    val scrim: Color,

    // extra colors
    val info: Color,
    val onInfo: Color,
    val infoContainer: Color,
    val onInfoContainer: Color,

    val success: Color,
    val onSuccess: Color,
    val successContainer: Color,
    val onSuccessContainer: Color,

    val warning: Color,
    val onWarning: Color,
    val warningContainer: Color,
    val onWarningContainer: Color,
)

internal fun ThemeColorScheme.toMaterialColorScheme(): ColorScheme {
    return ColorScheme(
        primary = primary,
        onPrimary = onPrimary,
        primaryContainer = primaryContainer,
        onPrimaryContainer = onPrimaryContainer,

        secondary = secondary,
        onSecondary = onSecondary,
        secondaryContainer = secondaryContainer,
        onSecondaryContainer = onSecondaryContainer,

        tertiary = tertiary,
        onTertiary = onTertiary,
        tertiaryContainer = tertiaryContainer,
        onTertiaryContainer = onTertiaryContainer,

        error = error,
        onError = onError,
        errorContainer = errorContainer,
        onErrorContainer = onErrorContainer,

        surfaceDim = surfaceDim,
        surface = surface,
        surfaceBright = surfaceBright,
        onSurface = onSurface,
        onSurfaceVariant = onSurfaceVariant,

        surfaceContainerLowest = surfaceContainerLowest,
        surfaceContainerLow = surfaceContainerLow,
        surfaceContainer = surfaceContainer,
        surfaceContainerHigh = surfaceContainerHigh,
        surfaceContainerHighest = surfaceContainerHighest,

        inverseSurface = inverseSurface,
        inverseOnSurface = inverseOnSurface,
        inversePrimary = inversePrimary,

        outline = outline,
        outlineVariant = outlineVariant,

        scrim = scrim,

        background = surface,
        onBackground = onSurface,
        surfaceVariant = surfaceContainerHighest,

        surfaceTint = surfaceContainerHighest,
    )
}

internal val LocalThemeColorScheme = staticCompositionLocalOf<ThemeColorScheme> {
    error("No ThemeColorScheme provided")
}
