package com.dfcruz.talkie.feature.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dfcruz.talkie.data.remote.websocket.Error
import com.dfcruz.talkie.data.remote.websocket.Message
import com.dfcruz.talkie.data.remote.websocket.OkHttpWebsocket
import com.dfcruz.talkie.data.remote.websocket.Websocket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ChatViewModel : ViewModel() {

    companion object {
        const val TAG = "ChatViewModel"
    }

    private val _uiState: MutableStateFlow<ChatUiState> =
        MutableStateFlow(ChatUiState(isLoading = true))
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    private var websocket: Websocket = OkHttpWebsocket()

    init {
        Log.i(TAG, "Started")
        try {
            websocket.connect()
                .flowOn(Dispatchers.IO)
                .catch { Log.e(TAG, "Flow Error", it) }
                .onEach {
                    when (it) {
                        is Error -> {
                            Log.e(TAG, "Socket Error", it.throwable)
                        }

                        is Message -> {
                            Log.i(TAG, "Socket Message - ${it.content}")
                        }
                    }
                }
                .launchIn(viewModelScope)
        } catch (t: Throwable) {
            Log.e(TAG, "Error", t)
        }
    }
}