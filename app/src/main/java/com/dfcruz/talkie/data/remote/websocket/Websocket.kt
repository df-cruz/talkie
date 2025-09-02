package com.dfcruz.talkie.data.remote.websocket

import kotlinx.coroutines.flow.Flow

interface Websocket {
    fun connect(): Flow<WebsocketResultEnvelope>
    fun disconnect()
    fun send(request: WebsocketEventEnvelope<Event>)
}