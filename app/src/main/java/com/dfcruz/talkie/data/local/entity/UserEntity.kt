package com.dfcruz.talkie.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: String,
    val name: String,
    val avatar: String = "",
    val online: Boolean = false,
    val createdAt: Date? = null,
    val updatedAt: Date? = null,
    val lastSeenAt: Date? = null
)