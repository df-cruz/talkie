package com.dfcruz.talkie.domain.usecase

import com.dfcruz.talkie.domain.Conversation
import com.dfcruz.talkie.domain.respositorie.ConversationRepository
import java.util.Date
import java.util.UUID
import javax.inject.Inject

class CreateConversationUseCase @Inject constructor(
    private val conversationRepository: ConversationRepository,
    private val getSessionUser: GetSessionUser,
) {
    suspend operator fun invoke() {
        val now = Date()
        val conversation = Conversation(
            id = UUID.randomUUID().toString(),
            conversationOwner = getSessionUser(),
            createdAt = now,
            updatedAt = now
        )
        conversationRepository.createConversation(conversation)
    }
}