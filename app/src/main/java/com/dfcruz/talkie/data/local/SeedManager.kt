package com.dfcruz.talkie.data.local

import com.dfcruz.talkie.data.local.dao.ConversationDao
import com.dfcruz.talkie.data.local.dao.MessageDao
import com.dfcruz.talkie.data.local.entity.ConversationEntity
import com.dfcruz.talkie.data.local.entity.MessageEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SeedManager @Inject constructor(
    private val conversationDao: ConversationDao,
    private val messageDao: MessageDao
) {
    fun seed() {
        CoroutineScope(Dispatchers.IO).launch {
            val count = conversationDao.getConversations()
            if (count.isNotEmpty()) return@launch // already seeded

            val now = Date()
            val conversationId = UUID.randomUUID().toString()

            val conversation = ConversationEntity(
                id = conversationId,
                conversationOwnerId = "system",
                name = "Welcome Chat",
                createdAt = now,
                updatedAt = now
            )
            conversationDao.insert(conversation)

            /*
            val messages = listOf(
                MessageEntity(
                    id = UUID.randomUUID().toString(),
                    conversationId = conversationId,
                    userId = "system",
                    text = "👋 Welcome! This is your first conversation.",
                    createdAt = now,
                    updatedAt = now
                ),
                MessageEntity(
                    id = UUID.randomUUID().toString(),
                    conversationId = conversationId,
                    userId = "system",
                    text = "Feel free to start chatting!",
                    createdAt = now,
                    updatedAt = now
                )
            )
            messageDao.insert(messages)

             */
        }
    }
}