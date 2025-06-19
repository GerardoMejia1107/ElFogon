package com.nullPointerSociety.elfogon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nullPointerSociety.elfogon.BuildConfig
import com.nullPointerSociety.elfogon.data.model.RecipeApi
import com.nullPointerSociety.elfogon.data.repository.spooncular.SpooncularRepositoryImplementation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SpooncularViewModel(
    private val repository: SpooncularRepositoryImplementation = SpooncularRepositoryImplementation()
) : ViewModel() {

    val recipes = repository.recipes

    // ✅ Nuevo: Estado para resultados de búsqueda en vivo
    private val _searchResults = MutableStateFlow<List<RecipeApi>>(emptyList())
    val searchResults: StateFlow<List<RecipeApi>> = _searchResults

    init {
        //fetchRecipes()
    }

    fun getRecipeById(id: Int): RecipeApi? {
        return repository.getRecipeById(id)
    }

    fun fetchRecipes() {
        viewModelScope.launch {
            repository.fetchRecipes(token = BuildConfig.SPOONACULAR_API_KEY, number = 10)
            // ✅ Actualiza también los searchResults con los datos iniciales
            _searchResults.value = repository.recipes.value
        }
    }

    fun fetchRecipesByCategory(category: String) {
        viewModelScope.launch {
            repository.fetchRecipesByTag(
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
}


