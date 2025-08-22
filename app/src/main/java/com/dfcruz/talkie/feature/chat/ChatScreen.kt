package com.dfcruz.talkie.feature.chat

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dfcruz.talkie.ui.component.TalkieMessageComposer
import com.dfcruz.talkie.ui.component.TalkieText
import com.dfcruz.talkie.ui.theme.TalkieTheme

@Composable
fun ChatScreen(
    viewModel: ChatViewModel = viewModel(),
    onBackPressed: () -> Unit
) {

    val chatUiState by viewModel.uiState.collectAsStateWithLifecycle()

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
            modifier = Modifier.fillMaxSize().padding(contentPadding),
            verticalArrangement = Arrangement.Bottom,
            reverseLayout = true,
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp)
        ) {
            items(chatUiState.messages, key = { it.id }) { message ->
                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = messageAlignmentPolicy(message.author)
                ) {
                    Bubble(
                        modifier = Modifier
                            .clickable {}
                            .widthIn(max = 280.dp),
                        color = bubbleColorPolicy(message.author)
                    ) {
                        TalkieText(
                            text = message.content,
                            style = TalkieTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun bubbleColorPolicy(messageAuthor: MessageAuthor): Color =
    when (messageAuthor) {
        MessageAuthor.Mine -> TalkieTheme.colors.primaryContainer
        MessageAuthor.Other -> TalkieTheme.colors.surfaceContainerHigh
    }

fun messageAlignmentPolicy(messageAuthor: MessageAuthor): Arrangement.Horizontal =
    when (messageAuthor) {
        MessageAuthor.Mine -> Arrangement.End
        MessageAuthor.Other -> Arrangement.Start
    }
