package com.dfcruz.talkie.domain

import java.util.Date

/**
 * Represents a message sent in a conversation.
 *
 * @property id Unique message identifier.
 * @property conversationId Identifier of the conversation this message belongs to.
 * @property text The textual content of the message.
 * @property user The sender of the message.
 * @property createdAt The timestamp when the message was created.
 * @property updatedAt The timestamp when the message was last updated.
 * @property deletedAt The timestamp when the message was deleted for everyone, or null if not deleted.
 * @property silent Whether this message was sent silently (no notifications).
 */
data class Message(
    val id: String = "",
    val conversationId: String = "",
    val text: String = "",
    val user: User = User(),
    val createdAt: Date? = null,
    val updatedAt: Date? = null,
    val deletedAt: Date? = null,
    val silent: Boolean = false
) {
    /**
     * Checks if this message has been deleted for everyone.
     */
    fun isDeleted(): Boolean = deletedAt != null

    /**
     * Returns true if this message was sent by the given [user].
     */
    fun isFrom(user: User): Boolean = this.user.id == user.id

    /**
     * Returns a short preview of the message text (e.g., for chat lists).
     *
     * @param maxLength The maximum length of the preview text.
     */
    fun preview(maxLength: Int = 30): String =
        if (text.length <= maxLength) text else text.take(maxLength) + "..."

    /**
     * Returns the age of the message in milliseconds, or null if [createdAt] is missing.
     */
    fun ageInMillis(): Long? = createdAt?.let { Date().time - it.time }
}