package com.dfcruz.talkie.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.dfcruz.talkie.data.local.entity.ConversationEntity
import com.dfcruz.talkie.data.local.relation.ConversationWithMembersAndMessages

@Dao
interface ConversationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(conversation: ConversationEntity)

    @Transaction
    @Query("SELECT * FROM conversations WHERE id = :conversationId")
    suspend fun getConversationWithMembersAndMessages(conversationId: String): ConversationWithMembersAndMessages?

    @Update
    suspend fun update(conversation: ConversationEntity)

    @Delete
    suspend fun delete(conversation: ConversationEntity)
}