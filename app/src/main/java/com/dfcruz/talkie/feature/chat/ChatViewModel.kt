package com.dfcruz.talkie.feature.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dfcruz.talkie.domain.respositorie.ConversationRepository
import com.dfcruz.talkie.domain.respositorie.MessageRepository
import com.dfcruz.talkie.domain.usecase.GetSessionUser
import com.dfcruz.talkie.domain.usecase.SendMessageUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

@HiltViewModel(assistedFactory = ChatViewModel.Factory::class)
class ChatViewModel @AssistedInject constructor(
    @Assisted private val conversationId: String,
    private val sendMessageUseCase: SendMessageUseCase,
    private val getSessionUser: GetSessionUser,
    private val conversationRepository: ConversationRepository,
    private val messagesRepository: MessageRepository
) : ViewModel() {

    companion object {
        const val TAG = "ChatViewModel"
    }

    private val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    private val _uiState: MutableStateFlow<ChatUiState> =
        MutableStateFlow(ChatUiState(isLoading = false, messages = emptyList()))
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            fetchMessages()

            combine(
                flowOf(conversationRepository.getConversation(conversationId)),
                messagesRepository.getMessagesFlow(conversationId)
            ) { conversation, messages ->
                ChatUiState(
                    isLoading = false,
                    conversationName = conversation?.getNameOrDefault().orEmpty(),
                    messages = messages.map { message ->
                        Message(
                            id = message.id,
                            content = MessageContent.Text(message.text),
                            createdAtLabel = message.createdAt?.let { date ->
                                formatter.format(date)
                            }.orEmpty(),
                            author = MessageAuthor.CurrentUser,
                        )
                    },
                    conversationType = if (conversation?.isGroup() == true) ConversationType.GROUP else ConversationType.DIRECT
                )
            }.collect { state ->
                _uiState.update { state }
            }
        }
    }

    fun fetchMessages() {
        viewModelScope.launch {
            messagesRepository.fetchMessagesFlow(conversationId)
        }
    }

    fun sendMessage(content: String) {
        viewModelScope.launch {
            val message = com.dfcruz.talkie.domain.Message(
                conversationId = conversationId,
                user = getSessionUser(),
                text = content.trim(),
            )
            sendMessageUseCase(message)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(conversationId: String): ChatViewModel
    }
}