package com.dfcruz.talkie.data.remote.rest.dto

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class ConversationResponse(
    val id: String,
    val conversationOwnerId: String? = null,
    val name: String? = null,
    val avatar: String? = null,
    val messageDraft: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
    val deletedAt: LocalDateTime? = null
)