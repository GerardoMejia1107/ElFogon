package com.nullPointerSociety.elfogon.data.model.recipes

data class RecipeApi(
    val id: Int,
    val title: String?,
    val image: String?,
    val readyInMinutes: Int?,
    val servings: Int?,
    val summary: String?,
    val extendedIngredients: List<ExtendedIngredient>,
    val analyzedInstructions: List<Instructions>,
)