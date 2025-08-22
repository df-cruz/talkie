package com.dfcruz.talkie.data.remote.websocket.model

import com.dfcruz.talkie.data.remote.websocket.WebsocketResponse
import kotlinx.serialization.Serializable

@Serializable
data class MessageResponse(
    val messageId: String,
    val chatId: String,
    val sender: Sender,
    val recipientIds: List<String>,
    val content: String,
    val type: String,
    val timestamp: String,       // ISO 8601 string
    val status: String,          // "sent", "delivered", "read"
    val isEdited: Boolean = false,
    val isDeleted: Boolean = false
) : WebsocketResponse

@Serializable
data class Sender(
    val id: String,
    val name: String,
    val avatarUrl: String? = null
)