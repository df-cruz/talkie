package com.dfcruz.talkie.feature.conversation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dfcruz.talkie.ui.component.TalkieAvatar
import com.dfcruz.talkie.ui.component.TalkieText
import com.dfcruz.talkie.ui.theme.TalkieTheme

@Composable
fun ConversationItem(
    modifier: Modifier = Modifier,
    avatarUrl: String,
    title: String,
    message: String,
    time: String,
    unreadMessageCount: Int,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
    ) {
        TalkieAvatar(image = avatarUrl, size = 48.dp)

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            TalkieText(
                text = title,
                style = TalkieTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            TalkieText(
                text = message,
                style = TalkieTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = modifier.wrapContentHeight(),
            horizontalAlignment = Alignment.End,
        ) {
            TalkieText(
                text = time,
                style = TalkieTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            if (unreadMessageCount > 0) {
                UnreadMessageIndicator(unreadMessageCount)
            }
        }
    }
}

@Composable
private fun UnreadMessageIndicator(
    unreadCount: Int,
) {
    Box(
        modifier = Modifier
            .size(18.dp)
            .background(
                color = TalkieTheme.colors.primaryContainer,
                shape = TalkieTheme.shapes.avatar
            ),
        contentAlignment = Alignment.Center,
    ) {
        TalkieText(
            color = TalkieTheme.colors.onPrimaryContainer,
            text = unreadCount.toString(),
            style = TalkieTheme.typography.labelSmall,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChatThreadPreview() {
    val conversation = ConversationUi(
        "1",
        "",
        "Alice Johnson",
        "Hey, are we still on for tomorrow?",
        "09:15",
        1
    )

    TalkieTheme {
        ConversationItem(
            avatarUrl = conversation.avatarUrl,
            title = conversation.title,
            message = conversation.messagePreview,
            time = conversation.messageTime,
            unreadMessageCount = conversation.unreadMessageCount,
        )
    }
}