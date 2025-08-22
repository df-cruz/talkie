package com.dfcruz.talkie.data

import com.dfcruz.talkie.data.remote.websocket.OkHttpWebsocket
import com.dfcruz.talkie.data.remote.websocket.Websocket
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindsMessagesRepository(impl: FakeMessagesRepository): MessagesRepository

    @Binds
    abstract fun bindsWebSocket(web: OkHttpWebsocket): Websocket
}