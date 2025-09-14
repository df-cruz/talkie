package com.dfcruz.talkie.data

import com.dfcruz.talkie.data.remote.rest.KtorService
import com.dfcruz.talkie.data.remote.rest.TalkieService
import com.dfcruz.talkie.data.remote.websocket.OkHttpWebsocket
import com.dfcruz.talkie.data.remote.websocket.Websocket
import com.dfcruz.talkie.domain.respositorie.ConversationRepository
import com.dfcruz.talkie.data.repository.ConversationRepositoryImpl
import com.dfcruz.talkie.domain.respositorie.MessageRepository
import com.dfcruz.talkie.data.repository.MessageRepositoryImpl
import com.dfcruz.talkie.domain.respositorie.UserRepository
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

    @Binds
    abstract fun bindTalkieService(service: KtorService): TalkieService
}