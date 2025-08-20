package com.dfcruz.talkie.data.remote.websocket

import kotlinx.coroutines.flow.Flow

interface Websocket {
    fun connect(): Flow<WebSocketEvent>
    fun disconnect()
    fun sendEvent(request: WebSocketRequest)
}