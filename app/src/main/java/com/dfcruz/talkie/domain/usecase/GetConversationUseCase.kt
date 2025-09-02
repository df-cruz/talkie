package com.dfcruz.talkie.domain.usecase

import com.dfcruz.talkie.data.repository.ConversationRepository
import com.dfcruz.talkie.domain.Conversation
import javax.inject.Inject

class GetConversationUseCase @Inject constructor(
    private val conversationRepository: ConversationRepository
) {
    suspend operator fun invoke(conversationId: String): Conversation? {
        return conversationRepository.getConversation(conversationId)
    }
}