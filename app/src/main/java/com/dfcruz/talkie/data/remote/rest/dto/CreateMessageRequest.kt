package com.dfcruz.talkie.data.remote.rest.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateMessageRequest(
    val userId: String? = null,
    val text: String,
    val silent: Boolean = false
)