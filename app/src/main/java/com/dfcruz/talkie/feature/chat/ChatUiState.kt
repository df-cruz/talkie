package com.dfcruz.talkie.feature.chat

data class ChatUiState(
    val isLoading: Boolean = true,
    val messages: List<Message> = emptyList(),
    val conversationType: ConversationType = ConversationType.DIRECT,
)
