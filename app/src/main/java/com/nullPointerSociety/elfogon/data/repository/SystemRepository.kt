package com.nullPointerSociety.elfogon.data.repository

import com.nullPointerSociety.elfogon.data.model.recipes.Recipe
import com.nullPointerSociety.elfogon.data.model.tips.Tip
import kotlinx.coroutines.flow.StateFlow

interface SystemRepository {
    val tips: StateFlow<List<Tip>>
    val customRecipes: StateFlow<List<Recipe>>

    suspend fun fetchTips()
    suspend fun fetchCustomRecipes()
    fun getRecipeById(id: Int): Recipe?
}
