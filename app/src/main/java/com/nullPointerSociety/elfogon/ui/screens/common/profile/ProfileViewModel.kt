package com.nullPointerSociety.elfogon.ui.screens.common.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.nullPointerSociety.elfogon.DelFogonApplication
import com.nullPointerSociety.elfogon.data.repository.AuthRepository
import com.nullPointerSociety.elfogon.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
) : ViewModel() {
    val authState = authRepository.authState
    val userData = authRepository.userData

    private val recipesSaved: MutableStateFlow<Int> = MutableStateFlow(-1)
    val savedRecipes: StateFlow<Int> = recipesSaved
    private val recipesMade: MutableStateFlow<Int> = MutableStateFlow(-1)
    val madeRecipes: StateFlow<Int> = recipesMade

    init {
        viewModelScope.launch {
            authRepository.checkAuthStatus()
            fetchSavedAndMadeRecipes()
        }
    }

    private fun fetchSavedAndMadeRecipes() {
        viewModelScope.launch {
            val uid = authRepository.getUserUid().toString()

            val saved =
                userRepository.getSavedRecipes(uid) + userRepository.getCustomSavedRecipes(uid)
            recipesSaved.value = saved.size

            val made = userRepository.getMadeRecipes(uid)
            recipesMade.value = made.size
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
                ProfileViewModel(
                    application.appProvider.provideAuthRepository(),
                    application.appProvider.provideUserRepository()
                )
            }
        }
    }

}