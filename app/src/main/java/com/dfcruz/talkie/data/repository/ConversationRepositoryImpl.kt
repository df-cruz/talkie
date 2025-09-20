package com.dfcruz.talkie.data.repository

import com.dfcruz.talkie.data.local.dao.ConversationDao
import com.dfcruz.talkie.data.local.entity.ConversationMemberEntity
import com.dfcruz.talkie.data.remote.rest.TalkieService
import com.dfcruz.talkie.domain.Conversation
import com.dfcruz.talkie.domain.respositorie.ConversationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class ConversationRepositoryImpl @Inject constructor(
    private val conversationDao: ConversationDao,
    private val talkieService: TalkieService
) : ConversationRepository {

    override fun getConversationsFlow(): Flow<List<Conversation>> {
        return conversationDao.getConversationsFlows()
            .map { conversations ->
                conversations.map { it.toDomain() }
            }
    }

    override suspend fun fetchConversations(userId: String) {
        talkieService.getAllConversations(userId).orNull()?.let { conversations ->
            conversations
                .takeIf { it.isNotEmpty() }
                ?.run {
                    conversationDao.insert(conversations = conversations.map { it.toEntity() })
                }
        }
    }

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

}