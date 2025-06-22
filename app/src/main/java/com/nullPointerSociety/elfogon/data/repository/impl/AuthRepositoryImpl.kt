package com.nullPointerSociety.elfogon.data.repository.impl


import android.util.Log
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.nullPointerSociety.elfogon.data.model.UserData
import com.nullPointerSociety.elfogon.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await

class AuthRepositoryImplementation(
    private val authService: FirebaseAuth,
    private val firestoreService: FirebaseFirestore
) : AuthRepository {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Unauthenticated)
    override val authState: StateFlow<AuthState> = _authState

    private val _userData = MutableStateFlow<UserData?>(null)
    override val userData: StateFlow<UserData?> = _userData


    override suspend fun checkAuthStatus() {
        if (authService.currentUser == null) {
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
            authService.signInWithEmailAndPassword(email, password).await()
            _authState.value = AuthState.Authenticated

            val userLogged = firestoreService.collection("users")
                .document(authService.currentUser?.uid.toString())
            val snapshot = userLogged.get().await()

            if (snapshot.exists()) {
                val name = snapshot.getString("name") ?: ""
                val lastName = snapshot.getString("lastName") ?: ""
                val profilePictureUrl = snapshot.getString("profilePictureUrl") ?: ""

                _userData.value = UserData(
                    email = authService.currentUser?.email.orEmpty(),
                    name = name,
                    lastName = lastName,
                    profilePictureUrl = profilePictureUrl
                )
            } else {
                Log.w("Firestore", "No document found for user")
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
            _authState.value = AuthState.Authenticated

            val docUser = hashMapOf(
                "email" to authService.currentUser?.email,
                "name" to name,
                "lastName" to lastName,
                "profilePictureUrl" to profilePictureUrl,
            )
            _userData.value = UserData(
                email = authService.currentUser?.email.toString(),
                name = name,
                lastName = lastName,
                profilePictureUrl = profilePictureUrl,
            )
            firestoreService.collection("users").document(authService.currentUser?.uid ?: "")
                .set(docUser).await()


        } catch (e: Exception) {
            _authState.value = AuthState.Error(e.message ?: "Sign up failed")
        }
    }

    override suspend fun logout() {
        authService.signOut()
        _authState.value = AuthState.Unauthenticated
    }

    override suspend fun signInWithGoogleCredential(credential: AuthCredential) {
        try {
            val user = authService.signInWithCredential(credential).await().user
            if (user != null && user.isAnonymous.not()) {
                _authState.value = AuthState.Authenticated

                _userData.value = UserData(
                    email = authService.currentUser?.email.toString(),
                    name = authService.currentUser?.displayName.toString(),
                    profilePictureUrl = authService.currentUser?.photoUrl?.toString()
                        ?.replace("s96-c", "s400-c"),
                )
            } else {
                _authState.value = AuthState.Error("No se pudo autenticar con Google.")
            }
        } catch (e: Exception) {
            _authState.value =
                AuthState.Error(e.message ?: "Error al iniciar sesión con Google.")
        }
    }


}

sealed class AuthState {
    object Loading : AuthState()
    data class Error(val message: String) : AuthState()
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
}