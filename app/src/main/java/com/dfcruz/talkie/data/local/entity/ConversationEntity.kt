package com.dfcruz.talkie.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "conversations")
data class ConversationEntity(
    @PrimaryKey val id: String,
    val conversationOwnerId: String? = null,
    val createdAt: Date? = null,
    val updatedAt: Date? = null,
    val deletedAt: Date? = null
)