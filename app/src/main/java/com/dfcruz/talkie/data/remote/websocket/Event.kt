package com.dfcruz.talkie.data.remote.websocket

import kotlinx.serialization.Serializable

@Serializable
sealed interface Event {
    val type: String
}

@Serializable
data class TypingEvent(
    val channelId: String,
    val userId: String,
    val action: Action
) : Event {
    @Serializable
    enum class Action { STARTED, STOPPED, UNKNOWN }

    override val type: String = "typing"
}

@Serializable
data class ChannelCreatedEvent(
    val channelId: String,
    val name: String?,
    val avatar: String?,
    val createdBy: String,
    val createdAt: Long
) : Event {
    override val type: String = "channel.created"
}

@Serializable
data class ChannelDeletedEvent(
    val channelId: String,
    val deletedBy: String,
    val deletedAt: Long
) : Event {
    override val type: String = "channel.deleted"
}

@Serializable
data class UserAddedToChannelEvent(
    val channelId: String,
    val userId: String,
    val addedBy: String,
    val addedAt: Long
) : Event {
    override val type: String = "user.added"
}

@Serializable
data class UserLeftChannelEvent(
    val channelId: String,
    val userId: String,
    val leftAt: Long
) : Event {
    override val type: String = "user.left"
}

@Serializable
data class ChannelNameChangedEvent(
    val channelId: String,
    val channelName: String,
    val changedBy: String,
    val changedAt: Long
) : Event {
    override val type: String = "channel.name.changed"
}

@Serializable
data class MessageSentEvent(
    val messageId: String,
    val channelId: String,
    val userId: String,
    val text: String,
    val createdAt: Long,
    val updatedAt: Long? = null
) : Event {
    override val type: String = "message.sent"
}

@Serializable
data class MessageDeletedEvent(
    val messageId: String,
    val channelId: String,
    val deletedBy: String,
    val deletedAt: Long
) : Event {
    override val type: String = "message.deleted"
}

@Serializable
data class UserPresenceEvent(
    val userId: String,
    val action: Action,
    val at: Long,
    val lastSeenAt: Long? = null
) : Event {
    @Serializable
    enum class Action { ONLINE, OFFLINE, UNKNOWN }

    override val type: String = "user.presence"
}