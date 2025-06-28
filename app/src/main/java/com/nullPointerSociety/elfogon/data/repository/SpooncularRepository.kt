package com.nullPointerSociety.elfogon.data.repository

import com.nullPointerSociety.elfogon.data.model.recipes.Recipe
import kotlinx.coroutines.flow.StateFlow

/**
 * Interfaz que define las operaciones para obtener recetas desde la API de Spoonacular.
 */
interface SpooncularRepository {

    val recipes: StateFlow<List<Recipe>>
    val recipeById: StateFlow<List<Recipe>>

    fun setMainRecipeList(recipes: List<Recipe>)
    fun getRecipeByIdFetched(id: String): Recipe?
    suspend fun fetchRecipes(token: String, number: Int = 10)
    suspend fun fetchRecipesByIdList(token: String, ids: List<String>)
    fun getRecipeSavedByIdFetched(id: String): Recipe?
    suspend fun fetchRecipesByTagDirect(
        token: String,
        tag: String,
        number: Int = 2
    ): List<Recipe>
}
