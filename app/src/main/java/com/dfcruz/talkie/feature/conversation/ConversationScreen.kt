package com.dfcruz.talkie.feature.conversation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dfcruz.talkie.ui.component.TalkieAvatar
import com.dfcruz.talkie.ui.component.TalkieIconButton
import com.dfcruz.talkie.ui.component.TalkieText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversationScreen(
    modifier: Modifier = Modifier,
    viewModel: ConversationsViewModel,
    openChat: (String) -> Unit
) {
    val conversations by viewModel.conversations.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.padding(horizontal = 12.dp),
                title = {
                    TalkieText("Chats")
                },
                navigationIcon = {
                    TalkieAvatar(size = 36.dp) {}
                },
                actions = {
                    TalkieIconButton(icon = Icons.Default.Create) {
                        openChat("")
                    }
                }
            )
        }
    ) { contentPaddings ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPaddings),
        ) {
            items(conversations, key = { it.id }) { message ->
                ConversationItem(
                    modifier = Modifier.clickable { openChat("") },
                    avatarUrl = message.avatarUrl,
                    title = message.title,
                    message = message.messagePreview,
                    time = message.messageTime,
                    unreadMessageCount = message.unreadMessageCount,
                )
            }
        }
    }
}