package com.dfcruz.talkie.domain.usecase

import com.dfcruz.talkie.domain.Conversation
import com.dfcruz.talkie.domain.respositorie.ConversationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetConversationsUseCase @Inject constructor(
    private val conversationRepository: ConversationRepository
) {
    operator fun invoke(): Flow<List<Conversation>> = flow {
        val userId = ""
        conversationRepository.fetchConversations(userId)
        emitAll(conversationRepository.getConversationsFlow(userId))
    }
}