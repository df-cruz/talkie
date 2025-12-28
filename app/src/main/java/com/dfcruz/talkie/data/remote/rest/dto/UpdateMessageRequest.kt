package com.dfcruz.talkie.data.remote.rest.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateMessageRequest(
    @SerialName("text")
    val text: String? = null,
    @SerialName("silent")
    val silent: Boolean? = null
)