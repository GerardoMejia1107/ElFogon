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
import com.nullPointerSociety.elfogon.data.repository.impl.SpooncularRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val authRepository: AuthRepository,
    private val spooncularRepository: SpooncularRepository
) : ViewModel() {
    val authState = authRepository.authState

    private val _searchResults = MutableStateFlow<List<RecipeApi>>(emptyList())
    val searchResults: StateFlow<List<RecipeApi>> = _searchResults

    private val _categorizedRecipes = MutableStateFlow<List<RecipeCategoryGroup>>(emptyList())
    val categorizedRecipes: StateFlow<List<RecipeCategoryGroup>> = _categorizedRecipes

    init {
        fetchCategorizedRecipes()
    }

    fun getRecipeById(id: Int): RecipeApi? {
        return categorizedRecipes.value.flatMap { it.recipes }.find { it.id == id }
    }

    fun fetchCategorizedRecipes() {
        viewModelScope.launch {
            val tags = listOf("vegan", "vegetarian", "dessert", "italian", "mexican", "breakfast", "soup", "snack")
            val categoryGroups = mutableListOf<RecipeCategoryGroup>()

            // ⚠️ Importante: usamos el método directo del implementation
            val realRepo = spooncularRepository as? SpooncularRepositoryImpl

            if (realRepo != null) {
                for (tag in tags) {
                    val fetched = realRepo.fetchRecipesByTagDirect(
                        token = BuildConfig.SPOONACULAR_API_KEY,
                        tag = tag,
                        number = 8
                    )
                    categoryGroups.add(RecipeCategoryGroup(tag.replaceFirstChar { it.uppercase() }, fetched))
                }
                _categorizedRecipes.value = categoryGroups
                _searchResults.value = emptyList()
            }
        }
    }

    fun filterRecipesByQuery(query: String) {
        if (query.isBlank()) {
            _searchResults.value = emptyList()
        } else {
            val allRecipes = categorizedRecipes.value.flatMap { it.recipes }
            _searchResults.value = allRecipes.filter {
                it.title?.contains(query, ignoreCase = true) ?: false
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as DelFogonApplication)
                HomeViewModel(
                    application.appProvider.provideAuthRepository(),
                    application.appProvider.provideSpooncularRepository()
                )
            }
        }
    }
}

data class RecipeCategoryGroup(
    val tag: String,
    val recipes: List<RecipeApi>
)
