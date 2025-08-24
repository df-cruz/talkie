package com.dfcruz.talkie.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dfcruz.talkie.ui.theme.TalkieTheme

// Scale relation between text and avatar size.
private const val INITIALS_SCALE_FACTOR = 0.55f

@Composable
fun TalkieAvatar(
    modifier: Modifier = Modifier,
    image: String = "",
    initials: String = "",
    isOnline: Boolean = false,
    size: Dp = 28.dp,
    onClick: (() -> Unit)? = null,
) {
    val baseAvatarModifier = Modifier
        .size(size)
        .clip(TalkieTheme.shapes.avatar)
        .background(TalkieTheme.colors.primaryContainer)
        .then(modifier)

    val clickableModifier = if (onClick != null) {
        Modifier.clickable(onClick = onClick)
    } else {
        Modifier
    }

    Box(
        modifier = Modifier
            .then(clickableModifier),
        contentAlignment = Alignment.Center
    ) {
        val contentModifier = Modifier.align(Alignment.Center)

        when {
            image.isNotBlank() -> {

                TalkieImage(
                    url = image,
                    contentDescription = "User Avatar",
                    contentScale = ContentScale.Crop,
                    modifier = baseAvatarModifier
                )
            }

            initials.isNotBlank() -> {
                val scaledFontSize = with(LocalDensity.current) {
                    (size * INITIALS_SCALE_FACTOR).toSp()
                }

                Box(
                    modifier = baseAvatarModifier,
                    contentAlignment = Alignment.Center
                ) {
                    TalkieText(
                        modifier = contentModifier,
                        text = initials,
                        fontSize = scaledFontSize
                    )
                }
            }

            else -> {
                Box(
                    modifier = baseAvatarModifier,
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Placeholder Avatar",
                        modifier = Modifier.size(size * 0.7f),
                        tint = TalkieTheme.colors.onPrimaryContainer
                    )
                }
            }
        }

        if (isOnline) {
            Box(
                modifier = Modifier
                    .size(size / 4f)
                    .align(Alignment.BottomEnd)
                    .offset(x = (-1).dp, y = (-1).dp)
                    .clip(CircleShape)
                    .background(Color.Green)

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TalkieAvatarPreview() {
    TalkieTheme {
        TalkieAvatar(
            initials = "JD",
            isOnline = true,
        )
    }
}