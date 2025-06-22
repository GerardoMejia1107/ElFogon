package com.nullPointerSociety.elfogon.ui.screens.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.nullPointerSociety.elfogon.DelFogonApplication
import com.nullPointerSociety.elfogon.data.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
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

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[APPLICATION_KEY] as DelFogonApplication)
                LoginViewModel(application.appProvider.provideAuthRepository())
            }
        }
    }


}