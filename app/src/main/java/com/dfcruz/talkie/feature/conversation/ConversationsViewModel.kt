package com.dfcruz.talkie.feature.conversation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dfcruz.talkie.domain.usecase.GetConversationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ConversationsViewModel @Inject constructor(
    getConversationsUseCase: GetConversationsUseCase
) : ViewModel() {

    val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())

    val conversations = getConversationsUseCase()
        .map {
            it.map { conversation ->
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
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            listOf()
        )
}