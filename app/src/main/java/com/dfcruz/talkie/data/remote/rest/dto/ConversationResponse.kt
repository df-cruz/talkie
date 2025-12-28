package com.dfcruz.talkie.data.remote.rest.dto

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConversationResponse(
    @SerialName("SerialName")
    val id: String,
    @SerialName("conversationOwnerId")
    val conversationOwnerId: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("avatar")
    val avatar: String? = null,
    @SerialName("messageDraft")
    val messageDraft: String? = null,
    @SerialName("createdAt")
    val createdAt: LocalDateTime? = null,
    @SerialName("updatedAt")
    val updatedAt: LocalDateTime? = null,
    @SerialName("deletedAt")
    val deletedAt: LocalDateTime? = null
)