package com.dfcruz.talkie.data.remote.websocket.model

import com.dfcruz.talkie.data.remote.websocket.WebSocketRequest
import kotlinx.serialization.Serializable

@Serializable
data class CreateMessageRequest(
    val chatId: String,
    val senderId: String,
    val recipientIds: List<String>,
    val content: String,
    val type: String,        // "text", "image", "video", "audio"
) : WebSocketRequest