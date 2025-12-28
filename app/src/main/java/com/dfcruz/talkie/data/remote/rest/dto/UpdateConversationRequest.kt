package com.dfcruz.talkie.data.remote.rest.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateConversationRequest(
    @SerialName("name")
    val name: String? = null,
    @SerialName("avatar")
    val avatar: String? = null,
    @SerialName("messageDraft")
    val messageDraft: String? = null
)