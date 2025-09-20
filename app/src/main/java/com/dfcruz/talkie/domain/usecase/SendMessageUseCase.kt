package com.dfcruz.talkie.domain.usecase

import com.dfcruz.talkie.domain.Message
import com.dfcruz.talkie.domain.respositorie.MessageRepository
import java.util.Date
import java.util.UUID
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val messagesRepository: MessageRepository
) {

    suspend operator fun invoke(message: Message) {
        if (message.conversationId.isBlank()) return

        messagesRepository.sendMessage(
            message.copy(
                id = message.id.ifBlank { UUID.randomUUID().toString() },
                createdAt = Date(),
                updatedAt = Date()
            )
        )
    }
}