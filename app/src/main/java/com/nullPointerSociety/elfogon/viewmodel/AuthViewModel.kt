package com.nullPointerSociety.elfogon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.firebase.auth.AuthCredential
import com.nullPointerSociety.elfogon.DelFogonApplication
import com.nullPointerSociety.elfogon.data.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val authRepository: AuthRepository) :
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

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[APPLICATION_KEY] as DelFogonApplication)
                AuthViewModel(
                    application.appProvider.provideAuthRepository()
                )
            }
        }
    }

}

