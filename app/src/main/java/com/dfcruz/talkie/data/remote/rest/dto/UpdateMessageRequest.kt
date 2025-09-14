package com.dfcruz.talkie.data.remote.rest.dto

import kotlinx.serialization.Serializable

@Serializable
data class UpdateMessageRequest(
    val text: String? = null,
    val silent: Boolean? = null
)