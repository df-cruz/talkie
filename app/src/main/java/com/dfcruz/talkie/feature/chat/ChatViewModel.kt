package com.dfcruz.talkie.feature.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dfcruz.talkie.data.MessagesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val messagesRepository: MessagesRepository
) : ViewModel() {

    companion object {
        const val TAG = "ChatViewModel"
    }

    private val _uiState: MutableStateFlow<ChatUiState> =
        MutableStateFlow(ChatUiState(isLoading = false, messages = sampleMessages.reversed()))
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    init {
        Log.i(TAG, "Started")
        try {
            viewModelScope.launch {
                messagesRepository.messages().collect { message ->
                    _uiState.update { it.copy(messages = it.messages + message) }
                }
            }
        } catch (t: Throwable) {
            Log.e(TAG, "Error", t)
        }
    }
}