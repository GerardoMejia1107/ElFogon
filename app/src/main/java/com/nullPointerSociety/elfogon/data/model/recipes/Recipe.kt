package com.nullPointerSociety.elfogon.data.model.recipes

data class Recipe(
    val id: Int = 0,
    val title: String = "",
    val image: String = "",
    val readyInMinutes: Int = 0,
    val servings: Int = 0,
    val summary: String = "",

    //Only used for the recipes brought from Firestore
    val months: List<Int> = emptyList(),
    val eventTags: List<String> = emptyList(),
    val festivity: List<String> = emptyList(),

    val extendedIngredients: List<ExtendedIngredient> = emptyList(),
    val analyzedInstructions: List<Instructions> = emptyList(),
)