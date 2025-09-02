package com.dfcruz.talkie.data.repository

import com.dfcruz.talkie.data.local.dao.MessageDao
import com.dfcruz.talkie.domain.Message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MessageRepositoryImpl @Inject constructor(
    private val messagesDao: MessageDao,
) : MessageRepository {

    override fun getMessagesFlow(conversationId: String): Flow<List<Message>> {
        return messagesDao.getMessagesFlow(conversationId).map { it.map { it.toDomain() } }
    }

    override suspend fun getMessages(conversationId: String): List<Message> {
        return messagesDao.getMessages(conversationId).map { it.toDomain() }
    }

    override suspend fun getMessage(id: String) {
        messagesDao.getMessage(id)
    }

    override suspend fun updateMessage(message: Message) {
        messagesDao.update(message.toEntity())
    }

    override suspend fun addMessage(message: Message) {
        messagesDao.insert(message.toEntity())
    }

    override suspend fun removeMessage(messageId: String) {
        messagesDao.delete(messageId)
    }
}