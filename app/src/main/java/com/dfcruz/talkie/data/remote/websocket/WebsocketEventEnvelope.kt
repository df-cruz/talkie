package com.dfcruz.talkie.data.remote.websocket

import kotlinx.serialization.Serializable

@Serializable
data class WebsocketEventEnvelope<out T : Event>(
    val type: String,
    val timestamp: Long,
    val payload: T,
)