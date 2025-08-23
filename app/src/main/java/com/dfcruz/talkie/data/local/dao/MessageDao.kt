package com.dfcruz.talkie.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.dfcruz.talkie.data.local.entity.MessageEntity
import com.dfcruz.talkie.data.local.relation.MessageWithUserAndReadBy

@Dao
interface MessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(message: MessageEntity)

    @Transaction
    @Query("SELECT * FROM messages WHERE id = :messageId")
    suspend fun getMessageWithUserAndReadBy(messageId: String): MessageWithUserAndReadBy?

    @Update
    suspend fun update(message: MessageEntity)

    @Delete
    suspend fun delete(message: MessageEntity)
}