package com.dfcruz.talkie.data

import com.dfcruz.talkie.feature.chat.Message
import kotlinx.coroutines.flow.Flow

interface MessagesRepository {
    fun messages(): Flow<Message>
}