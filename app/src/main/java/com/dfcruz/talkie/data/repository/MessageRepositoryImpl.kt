package com.dfcruz.talkie.data.repository

import com.dfcruz.talkie.data.local.dao.MessageDao
import com.dfcruz.talkie.data.remote.rest.TalkieService
import com.dfcruz.talkie.data.remote.rest.dto.CreateMessageRequest
import com.dfcruz.talkie.domain.Message
import com.dfcruz.talkie.domain.respositorie.MessageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MessageRepositoryImpl @Inject constructor(
    private val messagesDao: MessageDao,
    private val talkieService: TalkieService
) : MessageRepository {

    override fun getMessagesFlow(conversationId: String): Flow<List<Message>> {
        return messagesDao.getMessagesFlow(conversationId)
            .map { messages -> messages.map { it.toDomain() } }
    }

    override suspend fun fetchMessages(conversationId: String) {
        talkieService.getMessagesByConversation(conversationId).orNull()?.let { messages ->
            messages
                .takeIf { it.isNotEmpty() }
                ?.run {
                    messagesDao.insert(messages.map { it.toEntity() })
                }
        }
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

    override suspend fun sendMessage(message: Message) {
        val request = CreateMessageRequest(
            id = message.id,
            userId = message.user.id,
            text = message.text,
        )
        talkieService.createMessage(message.conversationId, request)
        messagesDao.insert(message.toEntity())
    }

    override suspend fun deleteMessage(messageId: String) {
        messagesDao.delete(messageId)
    }
}