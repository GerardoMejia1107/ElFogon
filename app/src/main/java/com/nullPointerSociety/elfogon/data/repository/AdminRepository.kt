package com.nullPointerSociety.elfogon.data.repository

import com.nullPointerSociety.elfogon.data.model.user.UserReceptor
import kotlinx.coroutines.flow.StateFlow

interface AdminRepository {
    val allUsers: StateFlow<List<UserReceptor>>
    val usersToday: StateFlow<List<UserReceptor>>
    suspend fun fetchAllUsers()
    suspend fun getUserById(uid: String): UserReceptor?
    suspend fun deleteUser(uid: String)

    suspend fun getUserCount(): Int
    suspend fun fetchNewUsersToday()

}