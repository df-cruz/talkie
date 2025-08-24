package com.dfcruz.talkie.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.dfcruz.talkie.ui.theme.TalkieTheme

@Composable
fun TalkieBubble(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.surface,
    shape: Shape = TalkieTheme.shapes.large,
    content: @Composable () -> Unit = {},
) = Surface(
    modifier = modifier,
    shape = shape,
    color = color,
    contentColor = contentColorFor(color),
    border = null
) {
    content()
}
