package com.nullPointerSociety.elfogon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.nullPointerSociety.elfogon.data.repository.AuthRepository
import com.nullPointerSociety.elfogon.data.repository.impl.AuthRepositoryImplementation
import kotlinx.coroutines.launch

class AuthViewModel(private val authRepository: AuthRepository = AuthRepositoryImplementation()) :
    ViewModel() {
    val authState = authRepository.authState
    val userData = authRepository.userData


    init {
        viewModelScope.launch {
            authRepository.checkAuthStatus()
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            authRepository.login(email, password)
        }
    }

    fun signUp(
        email: String,
        password: String,
        name: String,
        profilePictureUrl: String? = null,
        lastName: String
    ) {
        viewModelScope.launch {
            authRepository.signUp(email, password, name, profilePictureUrl, lastName)
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }

    fun signInWithGoogleCredential(credential: AuthCredential) {
        viewModelScope.launch {
            authRepository.signInWithGoogleCredential(credential)
        }
    }

}

