package com.nullPointerSociety.elfogon.data.repository

import com.nullPointerSociety.elfogon.data.model.RecipeApi
import kotlinx.coroutines.flow.StateFlow

interface SpooncularRepository {
    val recipes: StateFlow<List<RecipeApi>>
    fun getRecipeById(id: Int): RecipeApi?
    suspend fun fetchRecipes(token: String, number: Int = 100)
    suspend fun fetchRecipesByTag(token: String, tag: String, number: Int = 10)
}