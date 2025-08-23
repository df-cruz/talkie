package com.dfcruz.talkie.data.local.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.dfcruz.talkie.data.local.entity.MessageEntity
import com.dfcruz.talkie.data.local.entity.MessageReadByEntity
import com.dfcruz.talkie.data.local.entity.UserEntity

data class MessageWithUserAndReadBy(
    @Embedded
    val message: MessageEntity,

    @Relation(
        parentColumn = "userId",
        entityColumn = "id"
    )
    val sender: UserEntity?,

    @Relation(
        entity = UserEntity::class,
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = MessageReadByEntity::class,
            parentColumn = "messageId",
            entityColumn = "userId"
        )
    )
    val readBy: List<UserEntity>
)
