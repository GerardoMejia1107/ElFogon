package com.nullPointerSociety.elfogon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.nullPointerSociety.elfogon.BuildConfig
import com.nullPointerSociety.elfogon.DelFogonApplication
import com.nullPointerSociety.elfogon.data.model.RecipeApi
import com.nullPointerSociety.elfogon.data.repository.AuthRepository
import com.nullPointerSociety.elfogon.data.repository.SpooncularRepository
import com.nullPointerSociety.elfogon.data.repository.UserRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class UserViewModel(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository,
    private val spooncularRepository: SpooncularRepository


) : ViewModel() {

    private val _recipeIdSelected = MutableStateFlow<String>("")
    val recipeIdSelected: StateFlow<String> = _recipeIdSelected

    private val _eventChannel = Channel<String>()
    val eventFlow = _eventChannel.receiveAsFlow()

    fun setRecipeIdSelected(recipeId: String) {
        _recipeIdSelected.value = recipeId
    }

    init {
        viewModelScope.launch {
            spooncularRepository.fetchRecipesByIdList(
                BuildConfig.SPOONACULAR_API_KEY,
                userRepository.getSavedRecipes(authRepository.getUserUid().toString())
            )
        }
    }

    fun getListOfSavedRecipes(): StateFlow<List<RecipeApi>> {
        return spooncularRepository.recipeById
    }


    fun saveRecipe(recipeId: String?) {
        viewModelScope.launch {
            val uid = authRepository.getUserUid()
            if (uid != null) {
                try {
                    userRepository.updateSavedRecipes(uid, recipeId.toString())
                    _eventChannel.send("Recipe saved successfully")
                } catch (e: Exception) {
                    _eventChannel.send("Failed to save recipe: ${e.message}")
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
                    application.appProvider.provideSpooncularRepository()
                )
            }
        }
    }
}