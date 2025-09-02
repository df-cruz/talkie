package com.dfcruz.talkie.domain.usecase

import com.dfcruz.talkie.data.repository.ConversationRepository
import com.dfcruz.talkie.domain.Conversation
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetConversationsUseCase @Inject constructor(
    private val conversationRepository: ConversationRepository
) {
    operator fun invoke(): Flow<List<Conversation>> {
        return conversationRepository.getConversationsFlow()
    }
}