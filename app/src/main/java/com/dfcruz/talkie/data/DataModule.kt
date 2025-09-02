package com.dfcruz.talkie.data

import com.dfcruz.talkie.data.remote.websocket.OkHttpWebsocket
import com.dfcruz.talkie.data.remote.websocket.Websocket
import com.dfcruz.talkie.data.repository.ConversationRepository
import com.dfcruz.talkie.data.repository.ConversationRepositoryImpl
import com.dfcruz.talkie.data.repository.MessageRepository
import com.dfcruz.talkie.data.repository.MessageRepositoryImpl
import com.dfcruz.talkie.data.repository.UserRepository
import com.dfcruz.talkie.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindsMessageRepository(impl: MessageRepositoryImpl): MessageRepository

    @Binds
    abstract fun bindsConversationRepository(impl: ConversationRepositoryImpl): ConversationRepository

    @Binds
    abstract fun bindsUserRepository(impl: UserRepositoryImpl): UserRepository

    @Binds
    abstract fun bindsWebSocket(web: OkHttpWebsocket): Websocket
}