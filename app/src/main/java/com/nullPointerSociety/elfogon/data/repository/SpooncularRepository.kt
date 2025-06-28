package com.nullPointerSociety.elfogon.data.repository

import com.nullPointerSociety.elfogon.data.model.recipes.RecipeApi
import kotlinx.coroutines.flow.StateFlow

/**
 * Interfaz que define las operaciones para obtener recetas desde la API de Spoonacular.
 */
interface SpooncularRepository {

    val recipes: StateFlow<List<RecipeApi>>
    val recipeById: StateFlow<List<RecipeApi>>

    fun setMainRecipeList(recipes: List<RecipeApi>)
    fun getRecipeByIdFetched(id: Int): RecipeApi?
    suspend fun fetchRecipes(token: String, number: Int = 10)
    suspend fun fetchRecipesByIdList(token: String, ids: List<String>)
    fun getRecipeSavedByIdFetched(id: Int): RecipeApi?
    suspend fun fetchRecipesByTagDirect(
        token: String,
        tag: String,
        number: Int = 2
    ): List<RecipeApi>
}
