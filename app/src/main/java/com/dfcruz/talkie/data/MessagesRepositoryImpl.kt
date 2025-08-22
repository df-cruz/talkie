package com.dfcruz.talkie.data

import com.dfcruz.talkie.data.remote.websocket.Error
import com.dfcruz.talkie.data.remote.websocket.Websocket
import com.dfcruz.talkie.feature.chat.Message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MessagesRepositoryImpl @Inject constructor(
    private val websocket: Websocket
) : MessagesRepository {

    override fun messages(): Flow<Message> {
        return websocket.connect().map {
            when(it){
                is Error -> TODO()
                is com.dfcruz.talkie.data.remote.websocket.Message -> TODO()
            }
        }
    }
}