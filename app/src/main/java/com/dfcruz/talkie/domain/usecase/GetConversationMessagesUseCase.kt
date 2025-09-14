package com.dfcruz.talkie.domain.usecase

import com.dfcruz.talkie.domain.respositorie.MessageRepository
import com.dfcruz.talkie.domain.Message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetConversationMessagesUseCase @Inject constructor(
    private val messagesRepository: MessageRepository
) {
    operator fun invoke(conversationId: String): Flow<List<Message>> = flow {
        messagesRepository.fetchMessagesFlow(conversationId)
        emitAll(messagesRepository.getMessagesFlow(conversationId))
    }
}