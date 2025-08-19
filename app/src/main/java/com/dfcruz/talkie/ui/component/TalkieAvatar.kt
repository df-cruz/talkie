package com.dfcruz.talkie.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dfcruz.talkie.ui.theme.TalkieTheme

@Composable
fun TalkieAvatar(
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    onClick: (() -> Unit)? = null,
) {
    val clickableModifier = if (onClick != null) {
        Modifier.clickable(onClick = onClick)
    } else {
        Modifier
    }

    Box(
        modifier = modifier
            .size(size)
            .background(
                color = TalkieTheme.colors.primaryContainer,
                shape = TalkieTheme.shapes.avatar
            )
            .then(clickableModifier),
        contentAlignment = Alignment.Center,
    ) {
        Icon(Icons.Default.Person, contentDescription = null)
    }
}


@Preview(showBackground = true)
@Composable
fun TalkieAvatarPreview() {
    TalkieTheme {
        TalkieAvatar()
    }
}