package com.dfcruz.talkie.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp

@Immutable
data class ThemeSizes(
    val avatarSmall: Dp,
    val avatarMedium: Dp,
    val avatarLarge: Dp,
    val messageMaxWidth: Dp,
)

internal val LocalThemeSizes = staticCompositionLocalOf<ThemeSizes> {
    error("No ThemeSizes provided")
}
