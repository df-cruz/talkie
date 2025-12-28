package com.dfcruz.talkie.data.remote.rest.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateMessageRequest(
    @SerialName("id")
    val id: String,
    @SerialName("userId")
    val userId: String,
    @SerialName("text")
    val text: String,
    @SerialName("silent")
    val silent: Boolean = false
)