package com.dfcruz.talkie.feature.chat

data class ChatUiState(
    val isLoading: Boolean,
    val messages: List<Message> = emptyList(),
)
