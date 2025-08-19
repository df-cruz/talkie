package com.dfcruz.talkie.ui.theme.talkie

import androidx.compose.ui.graphics.Color
import com.dfcruz.talkie.ui.theme.ThemeColorScheme

val talkieColorScheme = ThemeColorScheme(
    primary = Color(0xFF1565C0), // Deep blue for main brand
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFF90CAF9), // Soft blue container
    onPrimaryContainer = Color(0xFF00274D),

    secondary = Color(0xFF00ACC1), // Cool cyan accent
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFB2EBF2),
    onSecondaryContainer = Color(0xFF00363D),

    tertiary = Color(0xFF7E57C2), // Muted violet for subtle highlights
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFD1C4E9),
    onTertiaryContainer = Color(0xFF2E1A47),

    error = Color(0xFFD32F2F),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFCD8DF),
    onErrorContainer = Color(0xFF370617),

    surfaceDim = Color(0xFFF4F6F8), // Cooler neutral
    surface = Color(0xFFFAFAFA),
    surfaceBright = Color(0xFFFFFFFF),
    onSurface = Color(0xFF212121),
    onSurfaceVariant = Color(0xFF5F6A6A),

    surfaceContainerLowest = Color(0xFFFFFFFF),
    surfaceContainerLow = Color(0xFFFDFDFD),
    surfaceContainer = Color(0xFFF7F9FA),
    surfaceContainerHigh = Color(0xFFF1F4F6),
    surfaceContainerHighest = Color(0xFFEBEEF0),

    inverseSurface = Color(0xFF2E2E2E),
    inverseOnSurface = Color(0xFFF0F8FF),
    inversePrimary = Color(0xFF64B5F6),

    outline = Color(0xFFB0BEC5),
    outlineVariant = Color(0xFFE0E0E0),

    scrim = Color(0x80000000),

    info = Color(0xFF0288D1),
    onInfo = Color(0xFFFFFFFF),
    infoContainer = Color(0xFFB3E5FC),
    onInfoContainer = Color(0xFF002F40),

    success = Color(0xFF388E3C), // Standard green for success messages
    onSuccess = Color(0xFFFFFFFF),
    successContainer = Color(0xFFC8E6C9),
    onSuccessContainer = Color(0xFF0A320D),

    warning = Color(0xFFFFA000), // Amber for warnings
    onWarning = Color(0xFF000000),
    warningContainer = Color(0xFFFFF3E0),
    onWarningContainer = Color(0xFF3B2E00)
)