package com.dfcruz.talkie.data.remote.websocket

import com.dfcruz.talkie.BuildConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import javax.inject.Inject

private const val EVENT_BUFFER_CAPACITY = 50

class OkHttpWebsocket @Inject constructor() : Websocket {

    private val client: OkHttpClient = OkHttpClient()
    private var socket: WebSocket? = null

    private val events: MutableSharedFlow<WebsocketResultEnvelope> =
        MutableSharedFlow(extraBufferCapacity = EVENT_BUFFER_CAPACITY)

    val socketListener = object : WebSocketListener() {
        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
            events.tryEmit(WebsocketResultEnvelope.Success(Json.decodeFromString<Event>(text)))
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            super.onFailure(webSocket, t, response)
            events.tryEmit(
                WebsocketResultEnvelope.Error(
                    response?.message.orEmpty(),
                    response?.code,
                    t
                )
            )
        }
    }

    override fun connect(): Flow<WebsocketResultEnvelope> {
        if (socket == null) {
            val request = Request.Builder().url(BuildConfig.BASE_URL + "/channel").build()
            socket = client.newWebSocket(request, socketListener)
        }
        return events.asSharedFlow()
    }

    override fun disconnect() {
        socket?.close(1000, "Disconnect")
        socket = null
    }

    override fun send(request: WebsocketEventEnvelope<Event>) {
        socket?.send(Json.encodeToString(request))
    }
}