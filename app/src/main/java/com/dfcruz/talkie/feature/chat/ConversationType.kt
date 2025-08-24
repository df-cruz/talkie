package com.dfcruz.talkie.feature.chat

/**
 * Represents the type of a conversation.
 *
 * Used to distinguish between private one-to-one chats
 * and group conversations with multiple participants.
 */
enum class ConversationType {
    /** A one-to-one conversation between two participants. */
    DIRECT,

    /** A conversation that involves multiple participants. */
    GROUP,
}