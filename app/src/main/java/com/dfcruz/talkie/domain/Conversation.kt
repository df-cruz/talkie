package com.dfcruz.talkie.domain

import java.util.Date

/**
 * Represents a conversation between users.
 *
 * @property id Unique identifier for the conversation.
 * @property avatarUrl The avatar url of the conversation.
 * @property name The name of the conversation.
 * @property messageDraft The draft message not yet submmitted.
 * @property messages The list of messages exchanged within the conversation.
 * @property members The users who are part of the conversation.
 * @property conversationOwner The user who owns or created the conversation, if applicable.
 * @property createdAt The timestamp when the conversation was created.
 * @property updatedAt The timestamp when the conversation was last updated.
 * @property deletedAt The timestamp when the conversation was deleted, or null if still active.
 */
data class Conversation(
    val id: String = "",
    val avatarUrl: String = "",
    val name: String = "",
    val messageDraft: String = "",
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

    /**
     * Returns the `name` if it is not null or blank.
     * Otherwise, returns the `name` properties of all members in the `members`.
     */
    fun getNameOrDefault(): String =
        name.ifBlank { null } ?: members.joinToString(separator = ", ") { it.name }

    /**
     * Returns the last message sent in this conversation
     */
    fun getLastMessage(): Message? = messages.lastOrNull()

}
