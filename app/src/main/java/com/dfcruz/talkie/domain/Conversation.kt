package com.dfcruz.talkie.domain

import java.util.Date

/**
 * Represents a conversation between users.
 *
 * @property id Unique identifier for the conversation.
 * @property messages The list of messages exchanged within the conversation.
 * @property members The users who are part of the conversation.
 * @property conversationOwner The user who owns or created the conversation, if applicable.
 * @property createdAt The timestamp when the conversation was created.
 * @property updatedAt The timestamp when the conversation was last updated.
 * @property deletedAt The timestamp when the conversation was deleted, or null if still active.
 */
data class Conversation(
    val id: String = "",
    val messages: List<Message> = emptyList(),
    val members: List<User> = emptyList(),
    val conversationOwner: User? = null,
    val createdAt: Date? = null,
    val updatedAt: Date? = null,
    val deletedAt: Date? = null,
) {
    /**
     * Checks if the conversation has been deleted.
     */
    fun isDeleted(): Boolean = deletedAt != null

    /**
     * Returns true if the given [user] is part of the conversation.
     */
    fun hasMember(user: User): Boolean = members.any { it.id == user.id }

    /**
     * Returns true if this conversation is a group chat (more than 2 members).
     */
    fun isGroup(): Boolean = members.size > 2

}
