package com.dfcruz.talkie.data

import com.dfcruz.talkie.feature.chat.Message
import com.dfcruz.talkie.feature.chat.MessageAuthor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeMessagesRepository @Inject constructor() : MessagesRepository {

    private val conversation = listOf(
        Message(
            id = 1,
            content = "Hey! How’s it going?",
            createdAt = "2025-08-21 10:00",
            author = MessageAuthor.Other
        ),
        Message(
            id = 2,
            content = "Pretty good, just finished work. You?",
            createdAt = "2025-08-21 10:01",
            author = MessageAuthor.Mine
        ),
        Message(
            id = 3,
            content = "Same here. Thinking about grabbing some food, wanna join?",
            createdAt = "2025-08-21 10:02",
            author = MessageAuthor.Other
        ),
        Message(
            id = 4,
            content = "Sure, I’m starving. Where should we go?",
            createdAt = "2025-08-21 10:03",
            author = MessageAuthor.Mine
        ),
        Message(
            id = 5,
            content = "How about that new ramen place downtown?",
            createdAt = "2025-08-21 10:04",
            author = MessageAuthor.Other
        ),
        Message(
            id = 6,
            content = "Perfect, let’s meet there in 30 minutes.",
            createdAt = "2025-08-21 10:05",
            author = MessageAuthor.Mine
        ),
        Message(
            id = 7,
            content = "Cool, I’ll leave in 10.",
            createdAt = "2025-08-21 10:06",
            author = MessageAuthor.Other
        ),
        Message(
            id = 8,
            content = "Actually, make it 15. Need to grab my wallet.",
            createdAt = "2025-08-21 10:06",
            author = MessageAuthor.Other
        ),
        Message(
            id = 9,
            content = "No worries, I’ll get ready too.",
            createdAt = "2025-08-21 10:07",
            author = MessageAuthor.Mine
        ),
        Message(
            id = 10,
            content = "Do you want me to pick you up on the way?",
            createdAt = "2025-08-21 10:07",
            author = MessageAuthor.Mine
        ),
        Message(
            id = 11,
            content = "That would be awesome, thanks!",
            createdAt = "2025-08-21 10:08",
            author = MessageAuthor.Other
        ),
        Message(
            id = 12,
            content = "Send me your location.",
            createdAt = "2025-08-21 10:08",
            author = MessageAuthor.Mine
        ),
        Message(
            id = 13,
            content = "Done. Just shared it with you.",
            createdAt = "2025-08-21 10:09",
            author = MessageAuthor.Other
        ),
        Message(
            id = 14,
            content = "Got it, I’ll be there in 20.",
            createdAt = "2025-08-21 10:10",
            author = MessageAuthor.Mine
        ),
        Message(
            id = 15,
            content = "Awesome. Can’t wait, I’m so hungry.",
            createdAt = "2025-08-21 10:11",
            author = MessageAuthor.Other
        ),
        Message(
            id = 16,
            content = "Same here. Haven’t eaten since breakfast.",
            createdAt = "2025-08-21 10:12",
            author = MessageAuthor.Mine
        ),
        Message(
            id = 17,
            content = "Oh wow, that’s rough.",
            createdAt = "2025-08-21 10:12",
            author = MessageAuthor.Other
        ),
        Message(
            id = 18,
            content = "Yeah, just had a crazy busy day at work.",
            createdAt = "2025-08-21 10:13",
            author = MessageAuthor.Mine
        ),
        Message(
            id = 19,
            content = "I know the feeling. Today was nonstop for me too.",
            createdAt = "2025-08-21 10:14",
            author = MessageAuthor.Other
        ),
        Message(
            id = 20,
            content = "Well, ramen will fix everything 🍜",
            createdAt = "2025-08-21 10:15",
            author = MessageAuthor.Mine
        )
    ).reversed()

    override fun messages(): Flow<Message> = flow {
        conversation.forEach {
            emit(it)
        }
    }
}