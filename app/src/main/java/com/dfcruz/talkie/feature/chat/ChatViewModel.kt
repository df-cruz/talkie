package com.dfcruz.talkie.feature.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dfcruz.talkie.domain.usecase.GetConversationMessagesUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

@HiltViewModel(assistedFactory = ChatViewModel.Factory::class)
class ChatViewModel @AssistedInject constructor(
    private val getConversationMessagesUseCase: GetConversationMessagesUseCase,
    @Assisted private val conversationId: String
) : ViewModel() {

    companion object {
        const val TAG = "ChatViewModel"
    }

    private val _uiState: MutableStateFlow<ChatUiState> =
        MutableStateFlow(ChatUiState(isLoading = false, messages = listOf()))
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    init {
        Log.i(TAG, "Started")
        try {
            viewModelScope.launch {
                val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
                getConversationMessagesUseCase(conversationId).collect { message ->
                    _uiState.update {
                        it.copy(messages = message.map { message ->
                            Message(
                                id = message.id,
                                content = MessageContent.Text(message.text),
                                createdAtLabel = message.createdAt?.let { date ->
                                    formatter.format(
                                        date
                                    )
                                }.orEmpty(),
                                author = MessageAuthor.CurrentUser,
                            )
                        })
                    }
                }
            }
        } catch (t: Throwable) {
            Log.e(TAG, "Error", t)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(conversationId: String): ChatViewModel
    }
}