package com.dfcruz.talkie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.dfcruz.talkie.feature.chat.ChatScreen
import com.dfcruz.talkie.feature.chat.ChatViewModel
import com.dfcruz.talkie.feature.conversation.ConversationScreen
import com.dfcruz.talkie.feature.conversation.ConversationsViewModel
import com.dfcruz.talkie.feature.createconversation.CreateConversationScreen
import com.dfcruz.talkie.navigation.Chat
import com.dfcruz.talkie.navigation.Conversations
import com.dfcruz.talkie.navigation.CreateConversation
import com.dfcruz.talkie.ui.theme.TalkieTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val backStack = rememberNavBackStack(Conversations)

            TalkieTheme {
                NavDisplay(
                    backStack = backStack,
                    onBack = { backStack.removeLastOrNull() },
                    entryDecorators = listOf(
                        rememberSceneSetupNavEntryDecorator(),
                        rememberSavedStateNavEntryDecorator(),
                        rememberViewModelStoreNavEntryDecorator()
                    ),
                    entryProvider = entryProvider {
                        entry<Conversations> { key ->
                            val viewModel = hiltViewModel<ConversationsViewModel>()
                            ConversationScreen(
                                viewModel = viewModel,
                                createConversation = { backStack.add(CreateConversation) },
                                openChat = { backStack.add(Chat(it)) })
                        }
                        entry<CreateConversation> { key ->
                            CreateConversationScreen() {
                                backStack.removeLastOrNull()
                            }
                        }
                        entry<Chat> { key ->
                            val viewModel = hiltViewModel<ChatViewModel, ChatViewModel.Factory>(
                                creationCallback = { factory ->
                                    factory.create(key.conversationId)
                                }
                            )
                            ChatScreen(viewModel = viewModel) {
                                backStack.removeLastOrNull()
                            }
                        }
                    }
                )
            }
        }
    }
}