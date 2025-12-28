package com.dfcruz.talkie.feature.chat.ui

import androidx.compose.runtime.staticCompositionLocalOf
import com.dfcruz.talkie.feature.chat.MessageAuthor
import com.dfcruz.talkie.feature.chat.MessageGroupPosition
import com.dfcruz.talkie.feature.chat.MessageUI

/**
 * Defines the rules for how certain parts of a chat message
 * (e.g., avatar, username) should be displayed in the UI.
 *
 * Different contexts (direct chat vs group chat) can apply
 * different visibility rules.
 */
interface MessageDisplayPolicy {

    /**
     * Determines whether the author's avatar should be shown for this message.
     */
    fun shouldShowAvatar(message: MessageUI): Boolean

    /**
     * Determines whether a placeholder should be reserved when the avatar is hidden,
     * in order to keep message bubbles aligned consistently.
     */
    fun shouldReserveAvatarSpace(message: MessageUI): Boolean

    /**
     * Determines whether the author's username should be shown for this message.
     */
    fun shouldShowUsername(message: MessageUI): Boolean
}

/**
 * Policy for direct messages (1-to-1 chat).
 *
 * In direct conversations, we typically don’t need to show
 * avatars or usernames because the context is clear.
 */
object DirectMessageDisplayPolicy : MessageDisplayPolicy {
    override fun shouldShowAvatar(message: MessageUI): Boolean = false
    override fun shouldReserveAvatarSpace(message: MessageUI): Boolean = false
    override fun shouldShowUsername(message: MessageUI): Boolean = false
}

/**
 * Policy for group chats.
 *
 * - Show the avatar if the message is from an external user
 *   and it is the *last message* in a consecutive group.
 *
 * - Show the username if the message is from an external user
 *   and it is the *first message* in a consecutive group.
 *
 * This helps visually group messages while still identifying
 * the speaker at the right places.
 */
object GroupMessageDisplayPolicy : MessageDisplayPolicy {
    override fun shouldShowAvatar(message: MessageUI): Boolean {
        return message.author is MessageAuthor.External &&
                message.groupPosition is MessageGroupPosition.Last
    }

    override fun shouldReserveAvatarSpace(message: MessageUI): Boolean {
        return message.author is MessageAuthor.External
    }

    override fun shouldShowUsername(message: MessageUI): Boolean {
        return message.groupPosition == MessageGroupPosition.First &&
                message.author is MessageAuthor.External
    }
}


val LocalMessageDisplayPolicy = staticCompositionLocalOf<MessageDisplayPolicy> {
    error("No MessageDisplayPolicy provided")
}