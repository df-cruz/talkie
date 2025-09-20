package com.dfcruz.talkie.feature.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dfcruz.talkie.ui.component.TalkieMessageComposer

@Composable
fun ChatScreen(
    viewModel: ChatViewModel,
    onBackPressed: () -> Unit
) {
    val chatUiState by viewModel.uiState.collectAsState()

    val visibilityPolicy = when (chatUiState.conversationType) {
        ConversationType.DIRECT -> DirectMessageDisplayPolicy
        ConversationType.GROUP -> GroupMessageDisplayPolicy
    }
    CompositionLocalProvider(
        LocalMessageDisplayPolicy provides visibilityPolicy,
    ) {
        Scaffold(
            topBar = {
                ChatTopBar(
                    avatarImage = null,
                    title = "John Doe",
                    onBackPressed = onBackPressed
                )
            },
            contentWindowInsets = WindowInsets(0)
        ) { contentPadding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
                    .navigationBarsPadding()
                    .imePadding()
            ) {
                MessagesView(
                    modifier = Modifier.weight(1f),
                    messages = chatUiState.messages,
                )
                TalkieMessageComposer(
                    modifier = Modifier,
                    text = ""
                ) { message ->
                    viewModel.sendMessage(message)
                }
            }
        }
    }
}

@Composable
fun MessagesView(
    modifier: Modifier = Modifier,
    messages: List<Message>
) {
    LazyColumn(
        modifier = modifier,
        reverseLayout = true,
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp)
    ) {
        items(messages, key = { it.id }) { message ->
            Spacer(Modifier.height(ChatBubblePolicy.spacing(message.groupPosition)))
            MessageRow(Modifier.animateItem(), message)
        }
    }
}