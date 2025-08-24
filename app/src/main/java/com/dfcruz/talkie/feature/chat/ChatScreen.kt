package com.dfcruz.talkie.feature.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dfcruz.talkie.ui.component.TalkieMessageComposer

@Composable
fun ChatScreen(
    viewModel: ChatViewModel = viewModel(),
    onBackPressed: () -> Unit
) {
    val chatUiState by viewModel.uiState.collectAsStateWithLifecycle()

    val visibilityPolicy = when (chatUiState.conversationType) {
        ConversationType.DIRECT -> DirectMessageDisplayPolicy
        ConversationType.GROUP -> GroupMessageDisplayPolicy
    }
    CompositionLocalProvider(
        LocalMessageDisplayPolicy provides visibilityPolicy,
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
                .consumeWindowInsets(WindowInsets.ime),
            topBar = {
                ChatTopBar(avatarImage = null, title = "John Doe", onBackPressed = onBackPressed)
            },
            bottomBar = {
                TalkieMessageComposer { }
            }
        ) { contentPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
                verticalArrangement = Arrangement.Bottom,
                reverseLayout = true,
                contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp)
            ) {
                items(chatUiState.messages, key = { it.id }) { message ->
                    Spacer(Modifier.height(ChatBubblePolicy.spacing(message.groupPosition)))
                    MessageRow(message)
                }
            }
        }
    }
}
