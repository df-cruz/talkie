package com.dfcruz.talkie.data.repository

import com.dfcruz.talkie.domain.Conversation
import kotlinx.coroutines.flow.Flow

interface ConversationRepository {
    suspend fun getConversation(conversationId: String): Conversation?
    suspend fun createConversation(conversation: Conversation)
    suspend fun updateConversation(conversation: Conversation)
    suspend fun deleteConversation(conversationId: String)
    suspend fun addUserToConversation(conversationId: String, userId: String)
    suspend fun removeUserFromConversation(conversationId: String, userId: String)

    fun getConversationsFlow(): Flow<List<Conversation>>
}