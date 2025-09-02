package com.dfcruz.talkie.data.repository

import com.dfcruz.talkie.data.local.dao.ConversationDao
import com.dfcruz.talkie.data.local.entity.ConversationMemberEntity
import com.dfcruz.talkie.domain.Conversation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ConversationRepositoryImpl @Inject constructor(
    private val conversationDao: ConversationDao,
) : ConversationRepository {

    override suspend fun getConversation(conversationId: String): Conversation? {
        return conversationDao.getConversation(conversationId)?.toDomain()
    }

    override suspend fun createConversation(conversation: Conversation) {
        conversationDao.insert(conversation.toEntity())
    }

    override suspend fun updateConversation(conversation: Conversation) {
        conversationDao.update(conversation.toEntity())
    }

    override suspend fun deleteConversation(conversationId: String) {
        conversationDao.delete(conversationId)
    }

    override suspend fun addUserToConversation(conversationId: String, userId: String) {
        conversationDao.addMember(ConversationMemberEntity(conversationId, userId))
    }

    override suspend fun removeUserFromConversation(conversationId: String, userId: String) {
        conversationDao.removeMember(conversationId, userId)
    }

    override fun getConversationsFlow(): Flow<List<Conversation>> {
        return conversationDao.getConversationsFlows().map { it.map { it.toDomain() } }
    }
}