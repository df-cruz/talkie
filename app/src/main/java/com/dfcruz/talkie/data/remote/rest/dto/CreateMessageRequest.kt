package com.dfcruz.talkie.data.remote.rest.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateMessageRequest(
    val id: String,
    val userId: String,
    val text: String,
    val silent: Boolean = false
)