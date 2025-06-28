package com.nullPointerSociety.elfogon.data.repository.impl

import com.nullPointerSociety.elfogon.data.api.RetrofitInstance
import com.nullPointerSociety.elfogon.data.model.recipes.RecipeApi
import com.nullPointerSociety.elfogon.data.repository.SpooncularRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class SpooncularRepositoryImpl : SpooncularRepository {
    private val _recipes = MutableStateFlow<List<RecipeApi>>(emptyList())
    override val recipes: StateFlow<List<RecipeApi>> = _recipes

    private val _savedRecipes = MutableStateFlow<List<RecipeApi>>(emptyList())
    override val recipeById: StateFlow<List<RecipeApi>> = _savedRecipes
    override fun setMainRecipeList(recipes: List<RecipeApi>) {
        _recipes.update { recipes }
    }


    //Me retorna una receta por ID de las recetas obtenidas (busca en la lista de recetas obtenidas (fetch para Home))
    override fun getRecipeByIdFetched(id: Int): RecipeApi? {
        return recipes.value.find { it.id == id }
    }

    //Me retorna una receta guardada por ID de las recetas guardadas (busca en la lista de recetas guardadas)
    override fun getRecipeSavedByIdFetched(id: Int): RecipeApi? {
        return recipeById.value.find { it.id == id }
    }


    // Fetch recipes from the Spoonacular API
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


    // Fetch recipes by ID list from the Spoonacular API
    override suspend fun fetchRecipesByIdList(token: String, ids: List<String>) {
        try {
            val allRecipes = mutableListOf<RecipeApi>()
            val chunks = ids.chunked(100)

            for (chunk in chunks) {
                val idsParam = chunk.joinToString(",")
                val response = RetrofitInstance.api.getRecipeByIdInfo(token, idsParam)
                allRecipes.addAll(response)
            }
            _savedRecipes.value = allRecipes
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // Devuelve recetas de una sola categoría
    override suspend fun fetchRecipesByTagDirect(
        token: String,
        tag: String,
        number: Int
    ): List<RecipeApi> {
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
