package com.dfcruz.talkie.data

import com.dfcruz.talkie.feature.chat.Message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeMessagesRepository @Inject constructor() : MessagesRepository {

    private val conversation = listOf<Message>().reversed()

    override fun messages(): Flow<Message> = flow {
        conversation.forEach {
            emit(it)
        }
    }
}