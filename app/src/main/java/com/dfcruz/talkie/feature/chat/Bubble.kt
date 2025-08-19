package com.dfcruz.talkie.feature.chat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dfcruz.talkie.ui.theme.TalkieTheme

@Composable
fun Bubble(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(color),
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = TalkieTheme.shapes.large,
        color = color,
        contentColor = contentColor,
        border = null
    ) {
        Box(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            content()
        }
    }
}
