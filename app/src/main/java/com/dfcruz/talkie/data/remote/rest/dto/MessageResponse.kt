package com.dfcruz.talkie.data.remote.rest.dto

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Serializable
data class MessageResponse(
    @SerialName("id")
    val id: String,
    @SerialName("conversationId")
    val conversationId: String,
    @SerialName("userId")
    val userId: String? = null,
    @SerialName("text")
    val text: String,
    @SerialName("status")
    val status: String,
    @SerialName("silent")
    val silent: Boolean = false,
    @SerialName("createdAt")
    val createdAt: LocalDateTime? = null,
    @SerialName("updatedAt")
    val updatedAt: LocalDateTime? = null,
    @SerialName("deletedAt")
    val deletedAt: LocalDateTime? = null
)