package com.dfcruz.talkie.data.remote.websocket

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WebsocketEventEnvelope<out T : Event>(
    @SerialName("type")
    val type: String,
    @SerialName("timestamp")
    val timestamp: Long,
    @SerialName("payload")
    val payload: T,
)