package com.dfcruz.talkie.data.repository

import com.dfcruz.talkie.data.local.entity.ConversationEntity
import com.dfcruz.talkie.data.local.entity.MessageEntity
import com.dfcruz.talkie.data.local.entity.UserEntity
import com.dfcruz.talkie.domain.Conversation
import com.dfcruz.talkie.domain.Message
import com.dfcruz.talkie.domain.User

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

fun MessageEntity.toDomain(user: User = User(id = userId ?: "")): Message {
    return Message(
        id = this.id,
        conversationId = this.conversationId,
        text = this.text,
        user = user,
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
        avatarUrl = this.avatar ?: "",
        name = this.name,
        messageDraft = this.messageDraft,
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