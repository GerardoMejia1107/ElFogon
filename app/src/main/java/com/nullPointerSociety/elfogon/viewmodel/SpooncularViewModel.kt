package com.nullPointerSociety.elfogon.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nullPointerSociety.elfogon.BuildConfig
import com.nullPointerSociety.elfogon.data.api.RetrofitInstance
import com.nullPointerSociety.elfogon.data.model.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SpooncularViewModel : ViewModel() {
    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> = _recipes

    init {
        fetchPosts()
    }

    fun getRecipeById(id: Int): Recipe? {
        return _recipes.value.find { it.id == id }
        //return com.nullPointerSociety.elfogon.ui.screens.recipes.find { it.id == id }

    }

    private fun fetchPosts() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getRecipes(
                    token = BuildConfig.SPOONACULAR_API_KEY,
                    number = 5
                )
                _recipes.value = response.recipes
            } catch (e: Exception) {
                Log.e("SpooncularViewModel", "Error fetching recipes", e)
            }
        }
    }

}