package com.dfcruz.talkie.data.remote.websocket

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

private const val BASE_URL = "https://example.com"
private const val EVENT_BUFFER_CAPACITY = 50

class OkHttpWebsocket : Websocket {

    private val client: OkHttpClient = OkHttpClient()
    private var socket: WebSocket? = null

    private val events: MutableSharedFlow<WebSocketEvent> =
        MutableSharedFlow(extraBufferCapacity = EVENT_BUFFER_CAPACITY)

    val socketListener = object : WebSocketListener() {
        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
            events.tryEmit(Message(text))
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            super.onFailure(webSocket, t, response)
            events.tryEmit(Error(t))
        }
    }

    override fun connect(): Flow<WebSocketEvent> {
        if (socket == null) {
            val request = Request.Builder().url(BASE_URL).build()
            socket = client.newWebSocket(request, socketListener)
        }
        return events.asSharedFlow()
    }

    override fun disconnect() {
        socket?.close(1000, "Disconnect")
        socket = null
    }

    override fun sendEvent(request: WebSocketRequest) {
        socket?.send(request.toString())
    }
}