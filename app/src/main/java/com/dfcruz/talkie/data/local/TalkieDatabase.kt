package com.dfcruz.talkie.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dfcruz.talkie.data.local.converter.DateConverter
import com.dfcruz.talkie.data.local.dao.ConversationDao
import com.dfcruz.talkie.data.local.dao.ConversationMemberDao
import com.dfcruz.talkie.data.local.dao.MessageDao
import com.dfcruz.talkie.data.local.dao.MessageReadByDao
import com.dfcruz.talkie.data.local.dao.UserDao
import com.dfcruz.talkie.data.local.entity.ConversationEntity
import com.dfcruz.talkie.data.local.entity.ConversationMemberEntity
import com.dfcruz.talkie.data.local.entity.MessageEntity
import com.dfcruz.talkie.data.local.entity.MessageReadByEntity
import com.dfcruz.talkie.data.local.entity.UserEntity

const val DATABASE_NAME = "talkie"

@Database(
    entities = [
        ConversationEntity::class,
        ConversationMemberEntity::class,
        MessageEntity::class,
        MessageReadByEntity::class,
        UserEntity::class,
    ],
    version = 1,
)
@TypeConverters(DateConverter::class)
abstract class TalkieDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun conversationDao(): ConversationDao
    abstract fun messageDao(): MessageDao
    abstract fun conversationMemberDao(): ConversationMemberDao
    abstract fun messageReadByDao(): MessageReadByDao
}