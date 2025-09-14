package com.dfcruz.talkie.data.repository

import com.dfcruz.talkie.data.local.dao.UserDao
import com.dfcruz.talkie.domain.User
import com.dfcruz.talkie.domain.respositorie.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
) : UserRepository {

    override suspend fun getUser(userId: String): User? {
        return userDao.getUser(userId)?.toDomain()
    }

    override suspend fun addUser(user: User) {
        userDao.insert(user.toEntity())
    }

    override suspend fun updateUser(user: User) {
        userDao.update(user.toEntity())
    }

    override suspend fun removeUser(userId: String) {
        userDao.delete(userId)
    }
}