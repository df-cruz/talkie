package com.dfcruz.talkie.feature.chat

data class Message(
    val id: Long,
    val content: String,
    val createdAt: String,
    val author: MessageAuthor,
)

enum class MessageAuthor {
    Mine, Other
}