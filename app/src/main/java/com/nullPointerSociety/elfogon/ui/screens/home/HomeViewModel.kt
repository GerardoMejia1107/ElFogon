package com.nullPointerSociety.elfogon.ui.screens.home

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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val authRepository: AuthRepository,
    private val spooncularRepository: SpooncularRepository
) : ViewModel() {
    val authState = authRepository.authState
    val recipes = spooncularRepository.recipes

    private val _searchResults = MutableStateFlow<List<RecipeApi>>(emptyList())
    val searchResults: StateFlow<List<RecipeApi>> = _searchResults


    init {
        //fetchRecipes()
    }

    fun getRecipeById(id: Int): RecipeApi? {
        return spooncularRepository.getRecipeById(id)
    }

    fun fetchRecipes() {
        viewModelScope.launch {
            spooncularRepository.fetchRecipes(token = BuildConfig.SPOONACULAR_API_KEY, number = 10)
            // ✅ Actualiza también los searchResults con los datos iniciales
            _searchResults.value = spooncularRepository.recipes.value
        }
    }

    fun fetchRecipesByCategory(category: String) {
        viewModelScope.launch {
            spooncularRepository.fetchRecipesByTag(
                token = BuildConfig.SPOONACULAR_API_KEY,
                tag = category
            )
        }
    }

//    cambios con elvis

    fun filterRecipesByQuery(query: String) {
        _searchResults.value = recipes.value.filter {
            it.title?.contains(query, ignoreCase = true) ?: true // Provide true if title is null
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[APPLICATION_KEY] as DelFogonApplication)

                HomeViewModel(
                    application.appProvider.provideAuthRepository(),
                    application.appProvider.provideSpooncularRepository()
                )
            }
        }
    }

}