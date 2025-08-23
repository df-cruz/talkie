package com.dfcruz.talkie.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dfcruz.talkie.data.local.entity.ConversationMemberEntity

@Dao
interface ConversationMemberDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMember(member: ConversationMemberEntity)

    @Query("DELETE FROM conversation_members WHERE conversationId = :conversationId AND userId = :userId")
    suspend fun removeMember(conversationId: String, userId: String)
}