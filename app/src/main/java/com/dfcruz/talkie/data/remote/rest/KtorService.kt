package com.dfcruz.talkie.data.remote.rest

import com.dfcruz.talkie.data.remote.rest.dto.ConversationResponse
import com.dfcruz.talkie.data.remote.rest.dto.CreateConversationRequest
import com.dfcruz.talkie.data.remote.rest.dto.CreateMessageRequest
import com.dfcruz.talkie.data.remote.rest.dto.MessageResponse
import com.dfcruz.talkie.data.remote.rest.dto.UpdateConversationRequest
import com.dfcruz.talkie.data.remote.rest.dto.UpdateMessageRequest
import com.dfcruz.talkie.util.Either
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class KtorService @Inject constructor(
    private val client: HttpClient
) : TalkieService {

    // Conversations
    override suspend fun getAllConversations(userId: String): Either<Throwable, List<ConversationResponse>> =
        sageRequest {
            client
                .get("conversations")
                .body<List<ConversationResponse>?>() ?: emptyList()
        }

    override suspend fun getConversationById(conversationId: String): Either<Throwable, ConversationResponse> =
        sageRequest {
            client.get("conversations/$conversationId").body()
        }

    override suspend fun createConversation(conversation: CreateConversationRequest): Either<Throwable, Unit> =
        sageRequest {
            client.post("conversations") {
                contentType(ContentType.Application.Json)
                setBody(conversation)
            }.body()
        }

    override suspend fun updateConversation(
        conversationId: String,
        conversation: UpdateConversationRequest
    ): Either<Throwable, Unit> = sageRequest {
        client.put("conversations/$conversationId") {
            contentType(ContentType.Application.Json)
            setBody(conversation)
        }.body()
    }

    override suspend fun deleteConversation(conversationId: String): Either<Throwable, Unit> =
        sageRequest {
            client.delete("conversations/$conversationId")
        }

    // Messages
    override suspend fun getMessagesByConversation(conversationId: String): Either<Throwable, List<MessageResponse>> =
        sageRequest {
            client
                .get("conversations/$conversationId/messages")
                .body<List<MessageResponse>?>() ?: emptyList()
        }

    override suspend fun getMessageById(
        conversationId: String,
        messageId: String
    ): Either<Throwable, MessageResponse> = sageRequest {
        client.get("conversations/$conversationId/messages/$messageId").body()
    }

    override suspend fun createMessage(
        conversationId: String,
        message: CreateMessageRequest
    ): Either<Throwable, Unit> = sageRequest {
        client.post("conversations/$conversationId/messages") {
            contentType(ContentType.Application.Json)
            setBody(message)
        }.body()
    }

    override suspend fun updateMessage(
        conversationId: String,
        messageId: String,
        message: UpdateMessageRequest
    ): Either<Throwable, Unit> = sageRequest {
        client.put("conversations/$conversationId/messages/$messageId") {
            contentType(ContentType.Application.Json)
            setBody(message)
        }.body()
    }

    override suspend fun deleteMessage(
        conversationId: String,
        messageId: String
    ): Either<Throwable, Unit> = sageRequest {
        client.delete("conversations/$conversationId/messages/$messageId")
    }

    suspend inline fun <T> sageRequest(
        block: suspend () -> T,
    ): Either<Throwable, T> {
        return Either.catch {
            block()
        }
    }
}