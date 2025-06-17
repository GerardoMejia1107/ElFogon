package com.nullPointerSociety.elfogon.data.repository.firebase.auth


import com.google.firebase.auth.AuthCredential
import com.nullPointerSociety.elfogon.data.model.UserData
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

    suspend fun logout()
    suspend fun signInWithGoogleCredential(credential: AuthCredential)


}