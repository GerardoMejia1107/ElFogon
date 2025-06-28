package com.nullPointerSociety.elfogon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.nullPointerSociety.elfogon.BuildConfig
import com.nullPointerSociety.elfogon.DelFogonApplication
import com.nullPointerSociety.elfogon.data.model.recipes.Recipe
import com.nullPointerSociety.elfogon.data.repository.SpooncularRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SpooncularViewModel(
    private val repository: SpooncularRepository
) : ViewModel() {

    val recipes = repository.recipes

    // ✅ Nuevo: Estado para resultados de búsqueda en vivo
    private val _searchResults = MutableStateFlow<List<Recipe>>(emptyList())
    val searchResults: StateFlow<List<Recipe>> = _searchResults

    init {
        fetchRecipes()
    }

    fun getRecipeById(id: Int): Recipe? {
        return repository.getRecipeByIdFetched(id)
    }

    fun fetchRecipes() {
        viewModelScope.launch {
            repository.fetchRecipes(token = BuildConfig.SPOONACULAR_API_KEY, number = 10)
            // ✅ Actualiza también los searchResults con los datos iniciales
            _searchResults.value = repository.recipes.value
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

                SpooncularViewModel(
                    application.appProvider.provideSpooncularRepository()
                )
            }
        }
    }
}


