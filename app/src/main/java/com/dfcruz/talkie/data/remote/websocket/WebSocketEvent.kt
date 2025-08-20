package com.dfcruz.talkie.data.remote.websocket

sealed interface WebSocketEvent

data class Message(val content: String) : WebSocketEvent

data class Error(val throwable: Throwable) : WebSocketEvent