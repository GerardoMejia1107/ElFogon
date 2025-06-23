package com.nullPointerSociety.elfogon.data.repository

import com.nullPointerSociety.elfogon.data.model.UserData

interface UserRepository {
    suspend fun getUserData(uid: String): UserData?
    suspend fun saveUserData(uid: String, userData: UserData)
}