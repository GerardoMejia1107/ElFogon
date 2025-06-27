package com.nullPointerSociety.elfogon.data.repository

import com.nullPointerSociety.elfogon.data.model.recipes.RecipeApi
import kotlinx.coroutines.flow.StateFlow

/**
 * Interfaz que define las operaciones para obtener recetas desde la API de Spoonacular.
 */
interface SpooncularRepository {

    /**
     * Flujo observable con la lista actual de recetas obtenidas.
     */
    val recipes: StateFlow<List<RecipeApi>>

    /**
     * Flujo observable con recetas obtenidas por ID.
     */
    val recipeById: StateFlow<List<RecipeApi>>

    /**
     * Devuelve una receta previamente obtenida por su ID, si existe en caché.
     *
     * @param id ID de la receta.
     * @return Receta encontrada o null si no está disponible.
     */
    fun getRecipeByIdFetched(id: Int): RecipeApi?



    /**
     * Obtiene una lista de recetas desde la API.
     *
     * @param token Token de autenticación.
     * @param number Número de recetas a obtener (por defecto 100).
     */
    suspend fun fetchRecipes(token: String, number: Int = 100)

    /**
     * Obtiene recetas por una lista de IDs.
     *
     * @param token Token de autenticación.
     * @param id Lista de IDs de recetas.
     */
    suspend fun fetchRecipesByIdList(token: String, id: List<String>)

    /**
     * Obtiene recetas por etiqueta.
     *
     * @param token Token de autenticación.
     * @param tag Etiqueta para filtrar recetas.
     * @param number Número de recetas a obtener (por defecto 10).
     */
    suspend fun fetchRecipesByTag(token: String, tag: String, number: Int = 10)

    fun getRecipeSavedByIdFetched(id: Int): RecipeApi?
}
