package com.nullPointerSociety.elfogon.ui.screens.client.recipes.made

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.nullPointerSociety.elfogon.BuildConfig
import com.nullPointerSociety.elfogon.DelFogonApplication
import com.nullPointerSociety.elfogon.data.model.recipes.Recipe
import com.nullPointerSociety.elfogon.data.repository.AuthRepository
import com.nullPointerSociety.elfogon.data.repository.SpooncularRepository
import com.nullPointerSociety.elfogon.data.repository.UserRepository
import com.nullPointerSociety.elfogon.ui.screens.client.recipes.saved.SavedRecipesViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MadeRecipesViewModel(
    private val userRepository: UserRepository,
    private val spooncularRepository: SpooncularRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            spooncularRepository.fetchRecipesByIdList(
                BuildConfig.SPOONACULAR_API_KEY,
                userRepository.getMadeRecipes(authRepository.getUserUid().toString())
            )


        }
    }

    fun getListOfMadeRecipes(): StateFlow<List<Recipe>> {
        return spooncularRepository.recipeById
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[APPLICATION_KEY] as DelFogonApplication)

                MadeRecipesViewModel(
                    application.appProvider.provideUserRepository(),
                    application.appProvider.provideSpooncularRepository(),
                    application.appProvider.provideAuthRepository()
                )
            }
        }
    }


}