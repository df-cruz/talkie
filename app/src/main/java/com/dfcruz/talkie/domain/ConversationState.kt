package com.dfcruz.talkie.domain

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ConversationState {

    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    private val _unreadMessageCount = MutableStateFlow(0)
    private val _members = MutableStateFlow<List<User>>(emptyList())
    private val _typingMembers = MutableStateFlow<Set<String>>(emptySet()) // user IDs
    private val _onlineMembers = MutableStateFlow<Set<String>>(emptySet()) // user IDs

    val messages: StateFlow<List<Message>> get() = _messages.asStateFlow()
    val unreadMessageCount: StateFlow<Int> get() = _unreadMessageCount.asStateFlow()
    val members: StateFlow<List<User>> get() = _members.asStateFlow()
    val typingMembers: StateFlow<Set<String>> get() = _typingMembers.asStateFlow()
    val onlineMembers: StateFlow<Set<String>> get() = _onlineMembers.asStateFlow()

    fun addMessage(message: Message, isUnread: Boolean = true) {
        _messages.update { it + message }
        if (isUnread) {
            _unreadMessageCount.value += 1
        }
    }

    fun setMessages(messages: List<Message>) {
        _messages.update { it + messages }
    }

    fun markAllRead() {
        _unreadMessageCount.value = 0
    }

    fun setMembers(newMembers: List<User>) {
        val newMemberIds = newMembers.map { it.id }.toSet()
        _members.value = newMembers

        // clean up presence states
        _onlineMembers.update { it.intersect(newMemberIds) }
        _typingMembers.update { it.intersect(newMemberIds) }

        // also update online members automatically from newMembers
        _onlineMembers.update { it + newMembers.filter { it.isOnline }.map { it.id } }
    }

    fun setTypingMembers(users: List<User>) {
        val validIds = _members.value.map { it.id }.toSet()
        _typingMembers.update { users.map { it.id }.toSet().intersect(validIds) }
    }

    fun userStartedTyping(user: User) {
        if (_members.value.any { it.id == user.id }) {
            _typingMembers.update { it + user.id }
        }
    }

    fun userStoppedTyping(user: User) {
        _typingMembers.update { it - user.id }
    }

    fun setUserOnline(userId: String, isOnline: Boolean) {
        _members.update {
            it.map {
                if (it.id == userId) it.copy(isOnline = isOnline) else it
            }
        }

        _onlineMembers.update {
            _members.value
                .filter { it.isOnline }
                .map { it.id }
                .toSet()
        }
    }

    // Helpers: convert IDs back to Users if needed in UI
    val typingUsers: List<User>
        get() = _members.value.filter { _typingMembers.value.contains(it.id) }

    val onlineUsers: List<User>
        get() = _members.value.filter { _onlineMembers.value.contains(it.id) }

}