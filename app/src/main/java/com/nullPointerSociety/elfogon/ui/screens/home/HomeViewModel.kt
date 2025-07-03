package com.nullPointerSociety.elfogon.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.nullPointerSociety.elfogon.BuildConfig
import com.nullPointerSociety.elfogon.DelFogonApplication
import com.nullPointerSociety.elfogon.data.model.recipes.Recipe
import com.nullPointerSociety.elfogon.data.repository.AuthRepository
import com.nullPointerSociety.elfogon.data.repository.SpooncularRepository
import com.nullPointerSociety.elfogon.data.repository.SystemRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val authRepository: AuthRepository,
    private val spooncularRepository: SpooncularRepository,
    private val systemRepository: SystemRepository
) : ViewModel() {

    // --- Authentication & tips ---
    val authState = authRepository.authState
    val tips = systemRepository.tips

    val customRecipes = systemRepository.customRecipes

    private val _hasShownTip = MutableStateFlow(false)
    val hasShownTip: StateFlow<Boolean> = _hasShownTip


    fun markTipAsShown() {
        _hasShownTip.value = true
    }

    // --- Recipe data ---
    private val _searchResults = MutableStateFlow<List<Recipe>>(emptyList())
    val searchResults: StateFlow<List<Recipe>> = _searchResults

    private val _categorizedRecipes = MutableStateFlow<List<RecipeCategoryGroup>>(emptyList())
    val categorizedRecipes: StateFlow<List<RecipeCategoryGroup>> = _categorizedRecipes

    private val _allRecipes = MutableStateFlow<List<Recipe>>(emptyList())

    init {
        //fetchTips()
        //fetchCustomRecipes()
        //fetchAllRecipesAndGrouped()
    }

    private fun fetchTips() {
        viewModelScope.launch {
            systemRepository.fetchTips()
        }
    }

    private fun fetchCustomRecipes() {
        viewModelScope.launch {
            systemRepository.fetchCustomRecipes()
        }
    }

    private fun fetchAllRecipesAndGrouped() {
        viewModelScope.launch {
            val tags = listOf(
                "vegan", "vegetarian", "dessert",
                "italian", "mexican", "breakfast",
                "soup", "snack"
            )

            val groups = mutableListOf<RecipeCategoryGroup>()
            val all = mutableListOf<Recipe>()


            for (tag in tags) {
                val list = spooncularRepository.fetchRecipesByTagDirect(
                    token = BuildConfig.SPOONACULAR_API_KEY,
                    tag = tag
                )
                groups += RecipeCategoryGroup(tag.replaceFirstChar { it.uppercase() }, list)
                all += list
            }


            _categorizedRecipes.value = groups
            _allRecipes.value = all
            spooncularRepository.setMainRecipeList(all)
        }
    }

    fun filterRecipesByQuery(query: String) {
        _searchResults.value = if (query.isBlank()) {
            emptyList()
        } else {
            _categorizedRecipes.value
                .flatMap { it.recipes }
                .filter { it.title.contains(query, ignoreCase = true) }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as DelFogonApplication
                HomeViewModel(
                    app.appProvider.provideAuthRepository(),
                    app.appProvider.provideSpooncularRepository(),
                    app.appProvider.provideSystemRepository()
                )
            }
        }
    }
}

data class RecipeCategoryGroup(
    val tag: String,
    val recipes: List<Recipe>
)
