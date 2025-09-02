package com.dfcruz.talkie.domain.usecase

import com.dfcruz.talkie.data.repository.MessageRepository
import com.dfcruz.talkie.domain.Message
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetConversationMessagesUseCase @Inject constructor(
    private val messagesRepository: MessageRepository
) {
    operator fun invoke(conversationId: String): Flow<List<Message>> {
        return messagesRepository.getMessagesFlow(conversationId)
    }
}