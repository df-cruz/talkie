package com.dfcruz.talkie.domain.respositorie

import com.dfcruz.talkie.domain.Message
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    fun getMessagesFlow(conversationId: String): Flow<List<Message>>
    suspend fun fetchMessages(conversationId: String)
    suspend fun getMessages(conversationId: String): List<Message>
    suspend fun getMessage(id: String)
    suspend fun updateMessage(message: Message)
    suspend fun sendMessage(message: Message)
    suspend fun deleteMessage(messageId: String)
}