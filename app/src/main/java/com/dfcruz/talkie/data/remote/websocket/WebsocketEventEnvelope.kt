package com.dfcruz.talkie.data.remote.websocket

import kotlinx.serialization.Serializable

@Serializable
data class WebsocketEventEnvelope<T: Event>(
    val type: String,
    val payload: T,
)