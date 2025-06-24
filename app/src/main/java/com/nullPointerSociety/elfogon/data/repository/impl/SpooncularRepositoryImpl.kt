package com.nullPointerSociety.elfogon.data.repository.impl

import com.nullPointerSociety.elfogon.data.api.RetrofitInstance
import com.nullPointerSociety.elfogon.data.model.RecipeApi
import com.nullPointerSociety.elfogon.data.repository.SpooncularRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SpooncularRepositoryImpl : SpooncularRepository {
    private val _recipes = MutableStateFlow<List<RecipeApi>>(emptyList())
    override val recipes: StateFlow<List<RecipeApi>> = _recipes

    override fun getRecipeById(id: Int): RecipeApi? {
        return recipes.value.find { it.id == id }
    }

    override suspend fun fetchRecipes(token: String, number: Int) {
        try {
            val response = RetrofitInstance.api.getRecipes(
                token = token,
                number = number
            )
            _recipes.value = response.recipes
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun fetchRecipesByTag(token: String, tag: String, number: Int) {
        try {
            val response = RetrofitInstance.api.getRecipes(
                token = token,
                number = number,
                tags = tag
            )
            _recipes.value = response.recipes
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // ✅ NUEVO MÉTODO para devolver recetas directamente sin sobrescribir el flujo
    suspend fun fetchRecipesByTagDirect(token: String, tag: String, number: Int = 8): List<RecipeApi> {
        return try {
            val response = RetrofitInstance.api.getRecipes(
                token = token,
                number = number,
                tags = tag
            )
            response.recipes
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
