package com.dfcruz.talkie.domain.respositorie

import com.dfcruz.talkie.domain.Conversation
import kotlinx.coroutines.flow.Flow

interface ConversationRepository {
    fun getConversationsFlow(): Flow<List<Conversation>>
    suspend fun fetchConversations(userId: String)
    suspend fun getConversation(conversationId: String): Conversation?
    suspend fun createConversation(conversation: Conversation)
    suspend fun updateConversation(conversation: Conversation)
    suspend fun deleteConversation(conversationId: String)
    suspend fun addUserToConversation(conversationId: String, userId: String)
    suspend fun removeUserFromConversation(conversationId: String, userId: String)
    suspend fun saveMessageDraft(text: String)
}