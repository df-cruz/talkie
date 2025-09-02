package com.dfcruz.talkie.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dfcruz.talkie.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE id = :userId LIMIT 1")
    suspend fun getUser(userId: String): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity)

    @Update
    suspend fun update(user: UserEntity)

    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun delete(userId: String)

    @Query(
        """
        SELECT u.* FROM users u
        INNER JOIN conversation_members cm ON cm.userId = u.id
        WHERE cm.conversationId = :conversationId
    """
    )
    fun getConversationMembersFlow(conversationId: String): Flow<List<UserEntity>>

    @Query(
        """
        SELECT u.* FROM users u
        INNER JOIN conversation_members cm ON cm.userId = u.id
        WHERE cm.conversationId = :conversationId
    """
    )
    suspend fun getUsersByConversationId(conversationId: String): List<UserEntity>
}