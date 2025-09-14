package com.dfcruz.talkie.data.remote.rest

import com.dfcruz.talkie.data.remote.rest.dto.ConversationResponse
import com.dfcruz.talkie.data.remote.rest.dto.CreateConversationRequest
import com.dfcruz.talkie.data.remote.rest.dto.CreateMessageRequest
import com.dfcruz.talkie.data.remote.rest.dto.MessageResponse
import com.dfcruz.talkie.data.remote.rest.dto.UpdateConversationRequest
import com.dfcruz.talkie.data.remote.rest.dto.UpdateMessageRequest

interface TalkieService {

    suspend fun getAllConversations(userId: String): List<ConversationResponse>
    suspend fun getConversationById(conversationId: String): ConversationResponse?
    suspend fun createConversation(conversation: CreateConversationRequest): ConversationResponse?
    suspend fun updateConversation(conversationId: String, conversation: UpdateConversationRequest): ConversationResponse?
    suspend fun deleteConversation(conversationId: String)

    suspend fun getMessagesByConversation(conversationId: String): List<MessageResponse>
    suspend fun getMessageById(conversationId: String, messageId: String): MessageResponse?
    suspend fun createMessage(conversationId: String, message: CreateMessageRequest): MessageResponse?
    suspend fun updateMessage(conversationId: String, messageId: String, message: UpdateMessageRequest): MessageResponse?
    suspend fun deleteMessage(conversationId: String, messageId: String)
}