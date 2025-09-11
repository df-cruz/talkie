package com.dfcruz.talkie.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface NavRoute : NavKey

@Serializable
data object Conversations : NavRoute

@Serializable
data object CreateConversation : NavRoute

@Serializable
data class Chat(val conversationId: String) : NavRoute