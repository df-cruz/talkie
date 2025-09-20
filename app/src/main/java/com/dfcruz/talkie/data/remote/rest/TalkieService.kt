package com.dfcruz.talkie.data.remote.rest

import com.dfcruz.talkie.data.remote.rest.dto.ConversationResponse
import com.dfcruz.talkie.data.remote.rest.dto.CreateConversationRequest
import com.dfcruz.talkie.data.remote.rest.dto.CreateMessageRequest
import com.dfcruz.talkie.data.remote.rest.dto.MessageResponse
import com.dfcruz.talkie.data.remote.rest.dto.UpdateConversationRequest
import com.dfcruz.talkie.data.remote.rest.dto.UpdateMessageRequest
import com.dfcruz.talkie.util.Either

interface TalkieService {

    suspend fun getAllConversations(userId: String): Either<Throwable, List<ConversationResponse>>
    suspend fun getConversationById(conversationId: String): Either<Throwable, ConversationResponse>
    suspend fun createConversation(conversation: CreateConversationRequest): Either<Throwable, Unit>
    suspend fun updateConversation(
        conversationId: String,
        conversation: UpdateConversationRequest
    ): Either<Throwable, Unit>

    suspend fun deleteConversation(conversationId: String): Either<Throwable, Unit>

    suspend fun getMessagesByConversation(conversationId: String): Either<Throwable, List<MessageResponse>>
    suspend fun getMessageById(
        conversationId: String,
        messageId: String
    ): Either<Throwable, MessageResponse>

    suspend fun createMessage(
        conversationId: String,
        message: CreateMessageRequest
    ): Either<Throwable, Unit>

    suspend fun updateMessage(
        conversationId: String,
        messageId: String,
        message: UpdateMessageRequest
    ): Either<Throwable, Unit>

    suspend fun deleteMessage(conversationId: String, messageId: String): Either<Throwable, Unit>
}