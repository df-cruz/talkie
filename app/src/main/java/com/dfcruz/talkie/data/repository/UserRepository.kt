package com.dfcruz.talkie.data.repository

import com.dfcruz.talkie.domain.User

interface UserRepository {
    suspend fun getUser(userId: String): User?
    suspend fun addUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun removeUser(userId: String)
}