package com.nullPointerSociety.elfogon.data.repository

import com.google.firebase.auth.AuthCredential
import com.nullPointerSociety.elfogon.data.model.user.UserData
import com.nullPointerSociety.elfogon.data.repository.impl.AuthState
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    val authState: StateFlow<AuthState>
    val userData: StateFlow<UserData?>

    suspend fun checkAuthStatus()
    suspend fun login(email: String, password: String)
    suspend fun signUp(
        email: String, password: String, name: String,
        profilePictureUrl: String?,
        lastName: String
    )

    suspend fun getUserUid(): String?

    suspend fun logout()
    suspend fun signInWithGoogleCredential(credential: AuthCredential)


}