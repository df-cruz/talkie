package com.dfcruz.talkie.data.remote.rest.dto

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Serializable
data class MessageResponse(
    val id: String,
    val conversationId: String,
    val userId: String? = null,
    val text: String,
    val silent: Boolean = false,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
    val deletedAt: LocalDateTime? = null
)