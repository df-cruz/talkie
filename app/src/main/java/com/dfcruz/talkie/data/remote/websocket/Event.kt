package com.dfcruz.talkie.data.remote.websocket

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface Event {
    @SerialName("type")
    val type: String
}

@Serializable
data class TypingEvent(
    @SerialName("channelId")
    val channelId: String,
    @SerialName("userId")
    val userId: String,
    @SerialName("action")
    val action: Action
) : Event {
    @Serializable
    enum class Action {
        @SerialName("STARTED")
        STARTED,

        @SerialName("STOPPED")
        STOPPED,

        @SerialName("UNKNOWN")
        UNKNOWN,
    }

    override val type: String = "typing"
}

@Serializable
data class ChannelCreatedEvent(
    @SerialName("channelId")
    val channelId: String,
    @SerialName("name")
    val name: String?,
    @SerialName("avatar")
    val avatar: String? = null,
    @SerialName("createdBy")
    val createdBy: String,
    @SerialName("createdAt")
    val createdAt: Long
) : Event {
    override val type: String = "channel.created"
}

@Serializable
data class ChannelDeletedEvent(
    @SerialName("channelId")
    val channelId: String,
    @SerialName("deletedBy")
    val deletedBy: String,
    @SerialName("deletedAt")
    val deletedAt: Long
) : Event {
    override val type: String = "channel.deleted"
}

@Serializable
data class UserAddedToChannelEvent(
    @SerialName("channelId")
    val channelId: String,
    @SerialName("userId")
    val userId: String,
    @SerialName("addedBy")
    val addedBy: String,
    @SerialName("addedAt")
    val addedAt: Long
) : Event {
    override val type: String = "user.added"
}

@Serializable
data class UserLeftChannelEvent(
    @SerialName("channelId")
    val channelId: String,
    @SerialName("userId")
    val userId: String,
    @SerialName("leftAt")
    val leftAt: Long
) : Event {
    override val type: String = "user.left"
}

@Serializable
data class ChannelNameChangedEvent(
    @SerialName("channelId")
    val channelId: String,
    @SerialName("channelName")
    val channelName: String,
    @SerialName("changedBy")
    val changedBy: String,
    @SerialName("changedAt")
    val changedAt: Long
) : Event {
    override val type: String = "channel.name.changed"
}

@Serializable
data class MessageSentEvent(
    @SerialName("messageId")
    val messageId: String,
    @SerialName("channelId")
    val channelId: String,
    @SerialName("userId")
    val userId: String,
    @SerialName("text")
    val text: String,
    @SerialName("createdAt")
    val createdAt: Long,
    @SerialName("updatedAt")
    val updatedAt: Long? = null
) : Event {
    override val type: String = "message.sent"
}

@Serializable
data class MessageDeletedEvent(
    @SerialName("messageId")
    val messageId: String,
    @SerialName("channelId")
    val channelId: String,
    @SerialName("deletedBy")
    val deletedBy: String,
    @SerialName("deletedAt")
    val deletedAt: Long
) : Event {
    override val type: String = "message.deleted"
}

@Serializable
data class UserPresenceEvent(
    @SerialName("userId")
    val userId: String,
    @SerialName("action")
    val action: Action,
    @SerialName("at")
    val at: Long,
    @SerialName("lastSeenAt")
    val lastSeenAt: Long? = null
) : Event {
    @Serializable
    enum class Action {
        @SerialName("STOPPED")
        ONLINE,

        @SerialName("OFFLINE")
        OFFLINE,

        @SerialName("UNKNOWN")
        UNKNOWN
    }

    override val type: String = "user.presence"
}