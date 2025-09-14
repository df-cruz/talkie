package com.dfcruz.talkie.feature.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dfcruz.talkie.domain.usecase.GetConversationMessagesUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.text.SimpleDateFormat
import java.util.Locale

@HiltViewModel(assistedFactory = ChatViewModel.Factory::class)
class ChatViewModel @AssistedInject constructor(
    getConversationMessagesUseCase: GetConversationMessagesUseCase,
    @Assisted private val conversationId: String
) : ViewModel() {

    companion object {
        const val TAG = "ChatViewModel"
    }

    private val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())

    val uiState: StateFlow<ChatUiState> = getConversationMessagesUseCase(conversationId)
        .map { messages ->
            ChatUiState(
                isLoading = false,
                messages = messages.map { message ->
                    Message(
                        id = message.id,
                        content = MessageContent.Text(message.text),
                        createdAtLabel = message.createdAt?.let { date ->
                            formatter.format(date)
                        }.orEmpty(),
                        author = MessageAuthor.CurrentUser,
                    )
                }
            )
        }
        .catch { t ->
            Log.e(TAG, "Error fetching messages", t)
            emit(ChatUiState(isLoading = false, messages = emptyList()))
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            ChatUiState(isLoading = false, messages = emptyList())
        )

    @AssistedFactory
    interface Factory {
        fun create(conversationId: String): ChatViewModel
    }
}