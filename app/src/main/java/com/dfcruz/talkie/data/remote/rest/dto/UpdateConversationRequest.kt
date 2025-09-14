package com.dfcruz.talkie.data.remote.rest.dto

import kotlinx.serialization.Serializable

@Serializable
data class UpdateConversationRequest(
    val name: String? = null,
    val avatar: String? = null,
    val messageDraft: String? = null
)