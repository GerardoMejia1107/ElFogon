package com.nullPointerSociety.elfogon.data.repository.firebase.auth


import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    val authState: StateFlow<AuthState>

    suspend fun checkAuthStatus()
    suspend fun login(email: String, password: String)
    suspend fun signUp(email: String, password: String)
    suspend fun logout()

}