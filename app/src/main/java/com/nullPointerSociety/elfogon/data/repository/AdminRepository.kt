package com.nullPointerSociety.elfogon.data.repository

import com.nullPointerSociety.elfogon.data.model.user.UserReceptor
import kotlinx.coroutines.flow.StateFlow

interface AdminRepository {
    val allUsers: StateFlow<List<UserReceptor>>
    suspend fun getUserById(uid: String): UserReceptor?
    suspend fun deleteUser(uid: String)

    suspend fun getUserCount(): Int
    suspend fun getNewUsersToday(): Int
    suspend fun getTopSavedRecipe(): Pair<String, Int>?
}