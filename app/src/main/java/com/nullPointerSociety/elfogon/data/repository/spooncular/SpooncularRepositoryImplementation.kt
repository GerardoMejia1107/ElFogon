package com.nullPointerSociety.elfogon.data.repository.spooncular

import com.nullPointerSociety.elfogon.data.api.RetrofitInstance
import com.nullPointerSociety.elfogon.data.model.RecipeApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SpooncularRepositoryImplementation : SpooncularRepository {
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


}