package com.dfcruz.talkie.feature.chat

data class ChatUiState(
    val isLoading: Boolean = true,
    val conversationName: String = "",
    val userInput: String = "",
    val messages: List<MessageUI> = emptyList(),
    val conversationType: ConversationType = ConversationType.DIRECT,
)
