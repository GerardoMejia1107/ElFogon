package com.nullPointerSociety.elfogon.data.repository.impl


import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.nullPointerSociety.elfogon.data.model.user.UserData
import com.nullPointerSociety.elfogon.data.repository.AuthRepository
import com.nullPointerSociety.elfogon.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await

class AuthRepositoryImplementation(
    private val authService: FirebaseAuth,
    private val userRepository: UserRepository
) : AuthRepository {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Unauthenticated)
    override val authState: StateFlow<AuthState> = _authState

    private val _userData = MutableStateFlow<UserData?>(null)
    override val userData: StateFlow<UserData?> = _userData


    override suspend fun fetchUserDataFromDB() {
        val uid = authService.currentUser?.uid ?: return
        _userData.value = userRepository.getUserData(uid)
    }


    override suspend fun checkAuthStatus() {
        if (authService.currentUser == null) {
            _authState.value = AuthState.Unauthenticated
        } else {
            _authState.value = AuthState.Authenticated
            _userData.value = userRepository.fetchUserData(authService.currentUser?.uid.toString())
        }
    }

    override suspend fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email and password cannot be empty")
            return
        }

        _authState.value = AuthState.Loading
        try {
            authService.signInWithEmailAndPassword(email, password).await()

            val userData = userRepository.getUserData(authService.currentUser?.uid ?: "")
            _userData.value = userData
            if (_userData.value != null) {
                _authState.value = AuthState.Authenticated
            }
        } catch (e: Exception) {
            _authState.value = AuthState.Error(e.message ?: "Login failed")
        }
    }

    override suspend fun signUp(
        email: String,
        password: String,
        name: String,
        profilePictureUrl: String?,
        lastName: String
    ) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email and password cannot be empty")
            return
        }
        _authState.value = AuthState.Loading
        try {
            authService.createUserWithEmailAndPassword(email, password).await()
            val userData = UserData(
                role = if (email == "00104923@uca.edu.sv") "admin" else "user",
                email = authService.currentUser?.email.toString(),
                name = name,
                lastName = lastName,
                profilePictureUrl = profilePictureUrl ?: ""
            )
            userRepository.saveUserData(authService.currentUser?.uid ?: "", userData)
            _userData.value = userData

            if (_userData.value != null) {
                _authState.value = AuthState.Authenticated
            }


        } catch (e: Exception) {
            _authState.value = AuthState.Error(e.message ?: "Sign up failed")
        }
    }

    override suspend fun getUserUid(): String? {
        return authService.currentUser?.uid
    }

    override suspend fun logout() {
        authService.signOut()
        _authState.value = AuthState.Unauthenticated
    }

    override suspend fun signInWithGoogleCredential(credential: AuthCredential) {
        try {
            val user = authService.signInWithCredential(credential).await().user
            if (user != null && !user.isAnonymous) {
                val uid = user.uid
                val existingUser = userRepository.getUserData(uid)

                if (existingUser != null) {
                    _userData.value = existingUser
                } else {
                    val newUser = UserData(
                        role = if (user.email == "00104923@uca.edu.sv") "admin" else "user",
                        email = user.email.orEmpty(),
                        name = user.displayName.orEmpty(),
                        profilePictureUrl = user.photoUrl?.toString()?.replace("s96-c", "s400-c")
                    )
                    userRepository.saveUserData(uid, newUser)
                    _userData.value = newUser
                }

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