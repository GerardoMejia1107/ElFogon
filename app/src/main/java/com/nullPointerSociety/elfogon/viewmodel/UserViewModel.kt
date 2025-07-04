package com.nullPointerSociety.elfogon.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.nullPointerSociety.elfogon.DelFogonApplication
import com.nullPointerSociety.elfogon.data.repository.AuthRepository
import com.nullPointerSociety.elfogon.data.repository.UserRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class UserViewModel(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _recipeIdSelected = MutableStateFlow<String>("")
    val recipeIdSelected: StateFlow<String> = _recipeIdSelected

    private val _eventChannel = Channel<String>()
    val eventFlow = _eventChannel.receiveAsFlow()

    fun setRecipeIdSelected(recipeId: String) {
        _recipeIdSelected.value = recipeId
        Log.d("UserViewModel", "Selected recipe ID: $recipeId")
    }

    fun saveRecipe(recipeId: String) {
        viewModelScope.launch {
            val uid = authRepository.getUserUid()
            if (uid != null) {
                try {
                    if (recipeId.startsWith("CUSTOM_")) {
                        userRepository.updateCustomSavedRecipes(uid, recipeId)
                    } else {
                        userRepository.updateSavedRecipes(uid, recipeId)
                    }
                    _eventChannel.send("Recipe saved successfully")
                } catch (e: Exception) {
                    _eventChannel.send("Failed to save recipe: ${e.message}")
                }
            }
        }
    }

    fun saveMadeRecipe(recipeId: String) {
        viewModelScope.launch {
            val uid = authRepository.getUserUid()
            if (uid != null) {
                try {
                    if (recipeId.startsWith("CUSTOM_")) {
                        userRepository.updateCustomMadeRecipes(uid, recipeId)
                    } else {
                        userRepository.updateMadeRecipes(uid, recipeId)
                    }
                    _eventChannel.send("Recipe marked as made successfully")
                } catch (e: Exception) {
                    _eventChannel.send("Failed to mark recipe as made: ${e.message}")
                }
            }
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[APPLICATION_KEY] as DelFogonApplication)

                UserViewModel(
                    application.appProvider.provideUserRepository(),
                    application.appProvider.provideAuthRepository(),
                )
            }
        }
    }
}