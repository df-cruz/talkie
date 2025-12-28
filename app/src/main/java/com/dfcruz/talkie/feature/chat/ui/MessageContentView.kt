package com.dfcruz.talkie.feature.chat.ui

import androidx.compose.runtime.Composable
import com.dfcruz.talkie.feature.chat.MessageContent
import com.dfcruz.talkie.ui.component.TalkieText
import com.dfcruz.talkie.ui.theme.TalkieTheme

/**
 * Renders the content inside a chat bubble.
 *
 * Supports text, single emoji, and system messages.
 *
 * @param content The content to display.
 */
@Composable
fun MessageContentView(content: MessageContent) {
    when (content) {
        is MessageContent.Text -> TalkieText(
            text = content.text,
            style = TalkieTheme.typography.bodyMedium
        )

        is MessageContent.Emoji -> TalkieText(
            text = content.emoji,
            style = TalkieTheme.typography.displayLarge
        )

        is MessageContent.System -> TalkieText(
            text = content.notice,
            style = TalkieTheme.typography.bodyMedium
        )
    }
}