package com.nullPointerSociety.elfogon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nullPointerSociety.elfogon.BuildConfig
import com.nullPointerSociety.elfogon.data.model.RecipeApi
import com.nullPointerSociety.elfogon.data.repository.spooncular.SpooncularRepositoryImplementation
import kotlinx.coroutines.launch

class SpooncularViewModel(
    private val repository: SpooncularRepositoryImplementation = SpooncularRepositoryImplementation()
) : ViewModel() {
    val recipes = repository.recipes

    init {
        fetchPosts()
    }

    fun getRecipeById(id: Int): RecipeApi? {
        return repository.getRecipeById(id)

    }

    private fun fetchPosts() {
        viewModelScope.launch {
            repository.fetchRecipes(token = BuildConfig.SPOONACULAR_API_KEY, number = 10)
        }
    }

}