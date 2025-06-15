package com.nullPointerSociety.elfogon.data.repository.firebase.auth


import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await

class AuthRepositoryImplementation(
) : AuthRepository {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _authState = MutableStateFlow<AuthState>(AuthState.Unauthenticated)

    override val authState: StateFlow<AuthState> = _authState


    override suspend fun checkAuthStatus() {
        if (auth.currentUser == null) {
            _authState.value = AuthState.Unauthenticated
        } else {
            _authState.value = AuthState.Authenticated
        }
    }

    override suspend fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email and password cannot be empty")
            return
        }

        _authState.value = AuthState.Loading
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            _authState.value = AuthState.Authenticated
        } catch (e: Exception) {
            _authState.value = AuthState.Error(e.message ?: "Login failed")
        }
    }

    override suspend fun signUp(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email and password cannot be empty")
            return
        }
        _authState.value = AuthState.Loading
        try {
            auth.createUserWithEmailAndPassword(email, password).await()
            _authState.value = AuthState.Authenticated
        } catch (e: Exception) {
            _authState.value = AuthState.Error(e.message ?: "Sign up failed")
        }
    }

    override suspend fun logout() {
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }

    override suspend fun signInWithGoogleCredential(credential: AuthCredential) {
        try {
            val user = auth.signInWithCredential(credential).await().user
            if (user != null && user.isAnonymous.not()) {
                _authState.value = AuthState.Authenticated
            } else {
                _authState.value = AuthState.Error("No se pudo autenticar con Google.")
            }
        } catch (e: Exception) {
            _authState.value = AuthState.Error(e.message ?: "Error al iniciar sesión con Google.")
        }
    }


}

sealed class AuthState {
    object Loading : AuthState()
    data class Error(val message: String) : AuthState()
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
}