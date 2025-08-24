package com.dfcruz.talkie.feature.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dfcruz.talkie.ui.theme.TalkieTheme

/**
 * Singleton object that provides all policies related to chat bubbles.
 *
 * This includes determining the bubble shape, background color, and row alignment
 * for messages depending on the author and position in a message group.
 */
object ChatBubblePolicy {

    fun spacing(position: MessageGroupPosition): Dp {
        return when (position) {
            MessageGroupPosition.First -> 4.dp
            is MessageGroupPosition.Middle -> 4.dp
            is MessageGroupPosition.Last -> 16.dp // The list of messages is displayed upside-down
        }
    }

    /**
     * Returns the appropriate bubble shape based on author and message position.
     */
    @Composable
    fun bubbleShape(author: MessageAuthor, position: MessageGroupPosition): Shape {
        return when (author) {
            MessageAuthor.CurrentUser -> when (position) {
                MessageGroupPosition.First -> TalkieTheme.shapes.bubbleFullRounded
                is MessageGroupPosition.Middle -> TalkieTheme.shapes.large
                is MessageGroupPosition.Last -> TalkieTheme.shapes.bubbleBottomRight
            }

            is MessageAuthor.External -> when (position) {
                MessageGroupPosition.First -> TalkieTheme.shapes.bubbleFullRounded
                is MessageGroupPosition.Middle -> TalkieTheme.shapes.bubbleFullRounded
                is MessageGroupPosition.Last -> TalkieTheme.shapes.bubbleBottomLeft
            }
        }
    }

    /**
     * Returns the bubble background color depending on the author.
     */
    @Composable
    fun bubbleColor(author: MessageAuthor): Color = when (author) {
        MessageAuthor.CurrentUser -> TalkieTheme.colors.primaryContainer
        is MessageAuthor.External -> TalkieTheme.colors.surfaceContainerHigh
    }

    /**
     * Returns the horizontal alignment for a message row based on author.
     */
    fun messageAlignment(author: MessageAuthor): Arrangement.Horizontal = when (author) {
        MessageAuthor.CurrentUser -> Arrangement.End
        is MessageAuthor.External -> Arrangement.Start
    }
}
