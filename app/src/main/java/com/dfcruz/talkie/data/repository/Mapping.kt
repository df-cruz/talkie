package com.dfcruz.talkie.data.repository

import com.dfcruz.talkie.data.local.entity.ConversationEntity
import com.dfcruz.talkie.data.local.entity.MessageEntity
import com.dfcruz.talkie.data.local.entity.UserEntity
import com.dfcruz.talkie.data.remote.rest.dto.ConversationResponse
import com.dfcruz.talkie.data.remote.rest.dto.MessageResponse
import com.dfcruz.talkie.domain.Conversation
import com.dfcruz.talkie.domain.Message
import com.dfcruz.talkie.domain.MessageStatus
import com.dfcruz.talkie.domain.User
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import java.util.Date
import kotlin.time.ExperimentalTime

fun UserEntity.toDomain(): User {
    return User(
        id = this.id,
        name = this.name,
        avatar = this.avatar,
        isOnline = this.online,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        lastSeenAt = this.lastSeenAt
    )
}

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        name = this.name,
        avatar = this.avatar,
        online = this.isOnline,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        lastSeenAt = this.lastSeenAt
    )
}

fun MessageEntity.toDomain(user: User = User(id = userId.orEmpty())): Message {
    return Message(
        id = this.id,
        conversationId = this.conversationId,
        text = this.text,
        user = user,
        status = MessageStatus.Companion.getStatus(status),
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        deletedAt = this.deletedAt,
        silent = this.silent
    )
}

fun Message.toEntity(): MessageEntity {
    return MessageEntity(
        id = this.id,
        conversationId = this.conversationId,
        userId = this.user.id.ifEmpty { null },
        text = this.text,
        status = status.name,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        deletedAt = this.deletedAt,
        silent = this.silent
    )
}

fun ConversationEntity.toDomain(
    messages: List<Message> = emptyList(),
    members: List<User> = emptyList(),
    conversationOwner: User? = conversationOwnerId?.let { User(id = it) }
): Conversation {
    return Conversation(
        id = this.id,
        avatarUrl = this.avatar.orEmpty(),
        name = this.name.orEmpty(),
        messageDraft = this.messageDraft.orEmpty(),
        messages = messages,
        members = members,
        conversationOwner = conversationOwner,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        deletedAt = this.deletedAt
    )
}

fun Conversation.toEntity(): ConversationEntity {
    return ConversationEntity(
        id = this.id,
        conversationOwnerId = this.conversationOwner?.id,
        name = this.name,
        avatar = this.avatarUrl,
        messageDraft = this.messageDraft,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        deletedAt = this.deletedAt
    )
}


fun MessageResponse.toEntity(): MessageEntity {
    return MessageEntity(
        id = id,
        conversationId = conversationId,
        userId = userId,
        text = text,
        status = MessageStatus.Companion.getStatus(status, default = MessageStatus.SYNCED).name,
        silent = silent,
        createdAt = createdAt?.toDate(),
        updatedAt = updatedAt?.toDate(),
        deletedAt = deletedAt?.toDate()
    )
}

fun ConversationResponse.toEntity(): ConversationEntity {
    return ConversationEntity(
        id = id,
        conversationOwnerId = conversationOwnerId,
        name = name,
        avatar = avatar,
        messageDraft = messageDraft,
        createdAt = createdAt?.toDate(),
        updatedAt = updatedAt?.toDate(),
        deletedAt = deletedAt?.toDate()
    )
}

@OptIn(ExperimentalTime::class)
private fun LocalDateTime.toDate(timeZone: TimeZone = TimeZone.UTC): Date =
    Date(this.toInstant(timeZone).toEpochMilliseconds())
