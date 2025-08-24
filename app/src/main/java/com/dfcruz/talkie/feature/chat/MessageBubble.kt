package com.dfcruz.talkie.feature.chat

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.dfcruz.talkie.ui.component.TalkieBubble
import com.dfcruz.talkie.ui.theme.TalkieTheme

@Composable
fun MessageBubble(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.surface,
    shape: Shape = TalkieTheme.shapes.large,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    topContent: @Composable ColumnScope.() -> Unit = {},
    centerContent: @Composable ColumnScope.() -> Unit = {},
    bottomContent: @Composable ColumnScope.() -> Unit = {}
) {
    TalkieBubble(
        modifier = modifier,
        color = color,
        shape = shape,
    ) {
        Column(
            modifier = Modifier
                .combinedClickable(
                    onClick = onClick,
                    onLongClick = onLongClick
                )
                .padding(
                    vertical = 8.dp,
                    horizontal = 12.dp
                )
        ) {
            topContent()
            centerContent()
            bottomContent()
        }
    }
}
