package com.dfcruz.talkie.feature.conversation

data class ConversationUi(
    val id: Long,
    val avatarUrl: String,
    val title: String,
    val messagePreview: String,
    val messageTime: String,
    val unreadMessageCount: Int,
)