package com.dfcruz.talkie.feature.conversation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dfcruz.talkie.domain.respositorie.ConversationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ConversationsViewModel @Inject constructor(
    private val conversationRepository: ConversationRepository
) : ViewModel() {

    private val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())

    private val _conversations: MutableStateFlow<List<ConversationUi>> = MutableStateFlow(listOf())
    val conversations: StateFlow<List<ConversationUi>> = _conversations.asStateFlow()

    init {
        viewModelScope.launch {
            launch {
                val userId = ""
                conversationRepository.fetchConversations(userId)
            }

            conversationRepository.getConversationsFlow()
                .collect { conversations ->
                    val conv = conversations.map { conversation ->
                        ConversationUi(
                            id = conversation.id,
                            avatarUrl = conversation.avatarUrl,
                            title = conversation.getNameOrDefault(),
                            messagePreview = conversation.getLastMessage()?.preview().orEmpty(),
                            messageTime = conversation.updatedAt
                                ?.let { date -> formatter.format(date) }
                                .orEmpty(),
                            unreadMessageCount = 0,
                        )
                    }
                    _conversations.update { conv }
                }
        }
    }
}