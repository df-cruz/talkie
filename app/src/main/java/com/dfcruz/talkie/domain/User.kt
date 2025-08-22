package com.dfcruz.talkie.domain

import java.util.Date

/**
 * Represents a user.
 *
 * @property id Unique identifier of the user.
 * @property name Display name of the user.
 * @property avatar URL of the user's profile image.
 * @property isOnline Whether the user is currently online.
 * @property createdAt When the account was created.
 * @property updatedAt When the account was last updated.
 * @property lastSeenAt The last time the user was active.
 */
data class User(
    val id: String = "",
    val name: String = "",
    val avatar: String = "",
    val isOnline: Boolean = false,
    val createdAt: Date? = null,
    val updatedAt: Date? = null,
    val lastSeenAt: Date? = null,
) {
    /**
     * Returns true if this user object is valid (has a non-empty [id]).
     */
    fun isValid(): Boolean = id.isNotBlank()

    /**
     * Returns initials from the user's [name], useful for avatars.
     *
     * Example:
     *  John Doe -> JD
     */
    fun initials(): String =
        name.trim()
            .split(" ")
            .filter { it.isNotBlank() }
            .map { it.first().uppercaseChar() }
            .joinToString("")
            .take(2)

    fun lastSeenAtInMillis(): Long? = lastSeenAt?.let { Date().time - it.time }
}