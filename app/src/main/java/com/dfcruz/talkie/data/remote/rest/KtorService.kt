package com.dfcruz.talkie.data.remote.rest

import com.dfcruz.talkie.data.remote.rest.dto.ConversationResponse
import com.dfcruz.talkie.data.remote.rest.dto.CreateConversationRequest
import com.dfcruz.talkie.data.remote.rest.dto.CreateMessageRequest
import com.dfcruz.talkie.data.remote.rest.dto.MessageResponse
import com.dfcruz.talkie.data.remote.rest.dto.UpdateConversationRequest
import com.dfcruz.talkie.data.remote.rest.dto.UpdateMessageRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import javax.inject.Inject

class KtorService @Inject constructor(
    private val client: HttpClient
) : TalkieService {

    // Conversations
    override suspend fun getAllConversations(userId: String): List<ConversationResponse> =
        client
            .get("conversations")
            .body<List<ConversationResponse>?>() ?: emptyList()


    override suspend fun getConversationById(conversationId: String): ConversationResponse? =
        client.get("conversations/$conversationId").body()

    override suspend fun createConversation(conversation: CreateConversationRequest): ConversationResponse? =
        client.post("conversations") {
            setBody(conversation)
        }.body()

    override suspend fun updateConversation(
        conversationId: String,
        conversation: UpdateConversationRequest
    ): ConversationResponse? =
        client.put("conversations/$conversationId") {
            setBody(conversation)
        }.body()

    override suspend fun deleteConversation(conversationId: String) {
        client.delete("conversations/$conversationId")
    }

    // Messages
    override suspend fun getMessagesByConversation(conversationId: String): List<MessageResponse> =
        client
            .get("conversations/$conversationId/messages")
            .body<List<MessageResponse>?>() ?: emptyList()

    override suspend fun getMessageById(
        conversationId: String,
        messageId: String
    ): MessageResponse? =
        client.get("conversations/$conversationId/messages/$messageId").body()

    override suspend fun createMessage(
        conversationId: String,
        message: CreateMessageRequest
    ): MessageResponse? =
        client.post("conversations/$conversationId/messages") {
            setBody(message)
        }.body()

    override suspend fun updateMessage(
        conversationId: String,
        messageId: String,
        message: UpdateMessageRequest
    ): MessageResponse? =
        client.put("conversations/$conversationId/messages/$messageId") {
            setBody(message)
        }.body()

    override suspend fun deleteMessage(conversationId: String, messageId: String) {
        client.delete("conversations/$conversationId/messages/$messageId")
    }
}