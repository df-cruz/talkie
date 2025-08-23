package com.dfcruz.talkie.data.local

import android.content.Context
import androidx.room.Room
import com.dfcruz.talkie.data.local.dao.ConversationDao
import com.dfcruz.talkie.data.local.dao.ConversationMemberDao
import com.dfcruz.talkie.data.local.dao.MessageDao
import com.dfcruz.talkie.data.local.dao.MessageReadByDao
import com.dfcruz.talkie.data.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext content: Context): TalkieDatabase {
        return Room.databaseBuilder(content, TalkieDatabase::class.java, "$DATABASE_NAME.db")
            .build()
    }

    @Provides
    @Singleton
    fun userDao(database: TalkieDatabase): UserDao = database.userDao()

    @Provides
    @Singleton
    fun conversationDao(database: TalkieDatabase): ConversationDao = database.conversationDao()

    @Provides
    @Singleton
    fun messageDao(database: TalkieDatabase): MessageDao = database.messageDao()

    @Provides
    @Singleton
    fun conversationMemberDao(database: TalkieDatabase): ConversationMemberDao =
        database.conversationMemberDao()

    @Provides
    @Singleton
    fun messageReadByDao(database: TalkieDatabase): MessageReadByDao = database.messageReadByDao()

}