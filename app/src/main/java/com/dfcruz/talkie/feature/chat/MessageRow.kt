package com.dfcruz.talkie.feature.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dfcruz.talkie.ui.component.TalkieAvatar
import com.dfcruz.talkie.ui.component.TalkieText
import com.dfcruz.talkie.ui.theme.TalkieTheme
import com.dfcruz.talkie.util.compose.PreviewColumn

@Composable
fun MessageRow(
    modifier: Modifier = Modifier,
    message: Message,
    onUserClick: (String) -> Unit = {},
    onMessagedClick: (String) -> Unit = {},
    onMessagedLongClick: (String) -> Unit = {},
) {
    val messageDisplayPolicy = LocalMessageDisplayPolicy.current

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = ChatBubblePolicy.messageAlignment(message.author)
    ) {
        when {
            messageDisplayPolicy.shouldShowAvatar(message) -> {
                MessageAuthorAvatar(
                    modifier = Modifier.align(Alignment.Bottom),
                    author = (message.author as MessageAuthor.External).details,
                    onClick = onUserClick
                )
                Spacer(modifier = Modifier.width(8.dp))
            }

            messageDisplayPolicy.shouldReserveAvatarSpace(message) -> {
                Spacer(modifier = Modifier.size(TalkieTheme.sizes.avatarSmall))
                Spacer(modifier = Modifier.width(8.dp))
            }
        }

        Column {
            if (messageDisplayPolicy.shouldShowUsername(message)) {
                MessageOwnerLabel(
                    name = (message.author as MessageAuthor.External).details.name,
                    modifier = Modifier
                        .padding(start = 12.dp, bottom = 4.dp)
                        .align(Alignment.Start)
                )
            }

            MessageBubble(
                modifier = Modifier.widthIn(max = TalkieTheme.sizes.messageMaxWidth),
                color = ChatBubblePolicy.bubbleColor(message.author),
                shape = ChatBubblePolicy.bubbleShape(message.author, message.groupPosition),
                onClick = { onMessagedClick(message.id) },
                onLongClick = { onMessagedLongClick(message.id) },
                centerContent = { MessageContentView(message.content) },
                bottomContent = {
                    MessageDeliveryTimeLabel(
                        modifier = Modifier.align(Alignment.End),
                        time = message.createdAtLabel,
                    )
                }
            )
        }
    }
}

@Composable
fun MessageAuthorAvatar(
    modifier: Modifier = Modifier,
    author: Author,
    onClick: (String) -> Unit
) {
    TalkieAvatar(
        modifier = modifier,
        image = author.avatarUrl.orEmpty(),
        initials = author.avatarInitials,
        size = TalkieTheme.sizes.avatarSmall,
        onClick = { onClick(author.id) },
    )
}

@Composable
fun MessageOwnerLabel(
    modifier: Modifier = Modifier,
    name: String
) {
    TalkieText(
        modifier = modifier,
        text = name,
        style = TalkieTheme.typography.labelMedium
    )
}

@Composable
fun MessageDeliveryTimeLabel(
    modifier: Modifier = Modifier,
    time: String
) {
    TalkieText(
        modifier = modifier,
        text = time,
        style = TalkieTheme.typography.labelSmall,
        textAlign = TextAlign.End
    )
}

@Preview
@Composable
fun MessageRowPreview() {
    CompositionLocalProvider(
        LocalMessageDisplayPolicy provides DirectMessageDisplayPolicy
    ) {
        PreviewColumn(modifier = Modifier, padding = PaddingValues(16.dp)) {

            // ----- Current User Messages -----
            MessageRow(
                message = Message(
                    id = "1",
                    content = MessageContent.Text("This is the first message from me (CurrentUser)."),
                    createdAtLabel = "09:10",
                    author = MessageAuthor.CurrentUser,
                    groupPosition = MessageGroupPosition.First
                )
            )
            Spacer(modifier = Modifier.height(4.dp))

            MessageRow(
                message = Message(
                    id = "2",
                    content = MessageContent.Text("Middle message from me, should have middle bubble shape."),
                    createdAtLabel = "09:11",
                    author = MessageAuthor.CurrentUser,
                    groupPosition = MessageGroupPosition.Middle(1)
                )
            )
            Spacer(modifier = Modifier.height(4.dp))

            MessageRow(
                message = Message(
                    id = "3",
                    content = MessageContent.Text("Last message from me, bottom-right bubble."),
                    createdAtLabel = "09:12",
                    author = MessageAuthor.CurrentUser,
                    groupPosition = MessageGroupPosition.Last(2)
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // ----- External User Messages -----
            MessageRow(
                message = Message(
                    id = "4",
                    content = MessageContent.Text("First message from other user."),
                    createdAtLabel = "09:13",
                    author = MessageAuthor.External(
                        details = Author(
                            id = "user_123",
                            name = "Alice",
                            avatarInitials = "A",
                            avatarUrl = null,
                            status = UserStatus.ONLINE
                        )
                    ),
                    groupPosition = MessageGroupPosition.First
                )
            )
            Spacer(modifier = Modifier.height(4.dp))

            MessageRow(
                message = Message(
                    id = "5",
                    content = MessageContent.Text("Middle message from other user."),
                    createdAtLabel = "09:14",
                    author = MessageAuthor.External(
                        details = Author(
                            id = "user_123",
                            name = "Alice",
                            avatarInitials = "A",
                            avatarUrl = null,
                            status = UserStatus.ONLINE
                        )
                    ),
                    groupPosition = MessageGroupPosition.Middle(1)
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            MessageRow(
                message = Message(
                    id = "6",
                    content = MessageContent.Text("Last message from other user."),
                    createdAtLabel = "09:15",
                    author = MessageAuthor.External(
                        details = Author(
                            id = "user_123",
                            name = "Alice",
                            avatarInitials = "A",
                            avatarUrl = null,
                            status = UserStatus.ONLINE
                        )
                    ),
                    groupPosition = MessageGroupPosition.Last(2)
                )
            )
        }
    }
}