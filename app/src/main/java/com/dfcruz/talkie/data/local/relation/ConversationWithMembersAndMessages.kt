package com.dfcruz.talkie.data.local.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.dfcruz.talkie.data.local.entity.ConversationEntity
import com.dfcruz.talkie.data.local.entity.ConversationMemberEntity
import com.dfcruz.talkie.data.local.entity.MessageEntity
import com.dfcruz.talkie.data.local.entity.UserEntity

data class ConversationWithMembersAndMessages(
    @Embedded
    val conversation: ConversationEntity,

    @Relation(
        entity = UserEntity::class,
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = ConversationMemberEntity::class,
            parentColumn = "conversationId",
            entityColumn = "userId"
        )
    )
    val members: List<UserEntity>,

    @Relation(
        entity = MessageEntity::class,
        parentColumn = "id",
        entityColumn = "conversationId"
    )
    val messages: List<MessageWithUserAndReadBy>
)