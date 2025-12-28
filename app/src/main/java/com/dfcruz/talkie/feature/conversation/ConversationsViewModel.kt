package com.dfcruz.talkie.feature.conversation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dfcruz.talkie.domain.respositorie.ConversationRepository
import com.dfcruz.talkie.domain.usecase.GetUserSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ConversationsViewModel @Inject constructor(
    private val conversationRepository: ConversationRepository,
    private val getSessionUser: GetUserSession,
) : ViewModel() {

    private val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())

    private val _conversations: MutableStateFlow<List<ConversationUi>> = MutableStateFlow(listOf())
    val conversations: StateFlow<List<ConversationUi>> = _conversations.asStateFlow()

    init {
        viewModelScope.launch {
            launch {
                conversationRepository.fetchConversations(getSessionUser().id)
            }

            conversationRepository.getConversationsFlow()
                .map { conversations ->
                    conversations.map { conversation ->
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
                }
                .collect { conversations ->
                    _conversations.update { conversations }
                }
        }
    }
}