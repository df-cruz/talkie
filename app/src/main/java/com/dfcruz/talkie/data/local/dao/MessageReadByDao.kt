package com.dfcruz.talkie.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dfcruz.talkie.data.local.entity.MessageReadByEntity

@Dao
interface MessageReadByDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun markRead(readBy: MessageReadByEntity)

    @Query("DELETE FROM message_read_by WHERE messageId = :messageId AND userId = :userId")
    suspend fun removeRead(messageId: String, userId: String)
}