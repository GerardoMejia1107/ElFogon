package com.nullPointerSociety.elfogon.ui.screens.client.recipes.saved

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.nullPointerSociety.elfogon.BuildConfig
import com.nullPointerSociety.elfogon.DelFogonApplication
import com.nullPointerSociety.elfogon.data.api.RetrofitInstance
import com.nullPointerSociety.elfogon.data.model.recipes.Recipe
import com.nullPointerSociety.elfogon.data.repository.AuthRepository
import com.nullPointerSociety.elfogon.data.repository.SpooncularRepository
import com.nullPointerSociety.elfogon.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SavedRecipesViewModel(
    private val userRepository: UserRepository,
    private val spooncularRepository: SpooncularRepository,
    private val authRepository: AuthRepository
) : ViewModel() {


    private val _customRecipesSaved = MutableStateFlow<List<Recipe>>(emptyList())
    val customRecipesSaved: StateFlow<List<Recipe>> = _customRecipesSaved

    init {
        viewModelScope.launch {
            spooncularRepository.fetchRecipesByIdList(
                BuildConfig.SPOONACULAR_API_KEY,
                userRepository.getSavedRecipes(authRepository.getUserUid().toString())
            )
            fetchCustomSavedRecipes()

        }
    }

    private fun fetchCustomSavedRecipes() {
        viewModelScope.launch {
            userRepository.getCustomSavedRecipes(authRepository.getUserUid().toString())
                .let { recipes ->
                    _customRecipesSaved.value = recipes
                }
        }
    }


    fun getListOfSavedRecipes(): StateFlow<List<Recipe>> {
        return spooncularRepository.recipeById
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[APPLICATION_KEY] as DelFogonApplication)

                SavedRecipesViewModel(
                    application.appProvider.provideUserRepository(),
                    application.appProvider.provideSpooncularRepository(),
                    application.appProvider.provideAuthRepository()
                )
            }
        }
    }
}