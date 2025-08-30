package com.dfcruz.talkie.data.remote.websocket

sealed interface WebsocketResultEnvelope {

    data class Success<out T : Event>(val data: T) : WebsocketResultEnvelope

    data class Error(
        val message: String,
        val code: Int? = null,
        val throwable: Throwable? = null
    ) : WebsocketResultEnvelope
}