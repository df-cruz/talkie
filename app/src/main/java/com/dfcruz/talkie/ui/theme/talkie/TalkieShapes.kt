package com.dfcruz.talkie.ui.theme.talkie

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import com.dfcruz.talkie.ui.theme.ThemeShapes

val talkieShapes = ThemeShapes(
    // Avatar
    avatar = CircleShape,

    // Material shapes
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(28.dp),

    // Bubble corners (top-left, top-right, bottom-left, bottom-right)

    bubbleBottomLeft = RoundedCornerShape(
        topStart = 16.dp,
        topEnd = 16.dp,
        bottomEnd = 16.dp,
        bottomStart = 0.dp
    ),
    bubbleBottomRight = RoundedCornerShape(
        topStart = 16.dp,
        topEnd = 16.dp,
        bottomEnd = 0.dp,
        bottomStart = 16.dp
    ),

    // Fully rounded bubble
    bubbleFullRounded = RoundedCornerShape(16.dp)
)