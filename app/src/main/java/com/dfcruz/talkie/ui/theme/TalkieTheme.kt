package com.dfcruz.talkie.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import com.dfcruz.talkie.ui.theme.talkie.talkieColorScheme
import com.dfcruz.talkie.ui.theme.talkie.talkieShapes
import com.dfcruz.talkie.ui.theme.talkie.talkieTypography


@Composable
fun TalkieTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(
        LocalThemeColorScheme provides talkieColorScheme,
        LocalThemeShapes provides talkieShapes,
        LocalThemeTypography provides talkieTypography,
    ) {
        MaterialTheme(
            colorScheme = talkieColorScheme.toMaterialColorScheme(),
            shapes = talkieShapes.toMaterialShapes(),
            typography = talkieTypography.toMaterialTypography(),
            content = content,
        )
    }
}

object TalkieTheme {

    val colors: ThemeColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalThemeColorScheme.current

    val shapes: ThemeShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalThemeShapes.current

    val typography: ThemeTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalThemeTypography.current
}
