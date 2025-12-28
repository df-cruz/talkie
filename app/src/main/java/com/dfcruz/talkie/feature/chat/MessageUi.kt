package com.dfcruz.talkie.feature.chat

/**
 * Represents a single message in a chat.
 *
 * @property id Unique identifier for the message.
 * @property content The actual message payload (text, emoji, or system notice).
 * @property createdAtLabel Pre-formatted timestamp (e.g., "09:10", "Yesterday").
 * @property author The sender of the message.
 * @property groupPosition The position in a sequence of messages from the same author.
 * @property isEdited Whether the message has been edited after sending.
 * @property isFailed Whether the message failed to send.
 * @property isRead Whether the message has been read by the recipient(s).
 * @property isSending Whether the message is still in the process of being sent.
 * @property isSending Whether the message is still in the process of being sent.
 */
data class MessageUI(
    val id: String,
    val content: MessageContent,
    val createdAtLabel: String,
    val author: MessageAuthor,
    val groupPosition: MessageGroupPosition = MessageGroupPosition.First,
    val isEdited: Boolean = false,
    val isFailed: Boolean = false,
    val isRead: Boolean = false,
    val isSending: Boolean = false,
    val isDeleted: Boolean = false,
)

/**
 * Represents the type of content a message can contain.
 * Currently supports only text-based messages.
 */
sealed interface MessageContent {

    /** Regular text message. */
    data class Text(val text: String) : MessageContent

    /** Message that contains only a single emoji. */
    data class Emoji(val emoji: String) : MessageContent

    /** System message (e.g., "User joined the chat"). */
    data class System(val notice: String) : MessageContent
}

/**
 * Represents the relative position of a message inside a group of
 * consecutive messages sent by the same user.
 *
 * Used for rendering grouped chat bubbles.
 *
 * @property index The relative index of the message within its group, starting at 0.
 */
sealed class MessageGroupPosition(open val index: Int) {

    /** The first message in the group (always at index 0). */
    data object First : MessageGroupPosition(0)

    /** A message that is neither the first nor the last in the group. */
    data class Middle(override val index: Int) : MessageGroupPosition(index)

    /** The last message in the group. */
    data class Last(override val index: Int) : MessageGroupPosition(index)
}

/**
 * Represents the author of a message in a chat.
 *
 * A message can either be authored by:
 * - [CurrentUser] → the logged-in user of the app
 * - [External] → another participant in the conversation
 */
sealed class MessageAuthor {

    /**
     * Message authored by the current logged-in user.
     */
    data object CurrentUser : MessageAuthor()

    /**
     * Message authored by another participant in the chat.
     *
     * @property details Metadata about the external author.
     */
    data class External(val details: Author) : MessageAuthor()
}

/**
 * Represents metadata about a chat participant (an external author).
 *
 * @property id Unique identifier for the user.
 * @property name Display name of the user.
 * @property avatarInitials Fallback initials for avatar rendering.
 * @property avatarUrl Optional URL to the user’s avatar image.
 * @property status Whether the user is currently online or offline.
 */
data class Author(
    val id: String,
    val name: String,
    val avatarInitials: String,
    val avatarUrl: String? = null,
    val status: UserStatus = UserStatus.OFFLINE,
)

/**
 * Represents the availability status of a chat participant.
 */
enum class UserStatus {
    ONLINE,
    OFFLINE
}