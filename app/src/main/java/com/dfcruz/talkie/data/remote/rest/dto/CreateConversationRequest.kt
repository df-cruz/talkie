package com.dfcruz.talkie.data.remote.rest.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateConversationRequest(
    @SerialName("conversationOwnerId")
    val conversationOwnerId: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("avatar")
    val avatar: String? = null,
    @SerialName("messageDraft")
    val messageDraft: String? = null
)