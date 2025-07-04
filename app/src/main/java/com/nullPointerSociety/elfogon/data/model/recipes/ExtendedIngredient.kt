package com.nullPointerSociety.elfogon.data.model.recipes

data class ExtendedIngredient(
    val id: Int = 0,
    val nameClean: String = "",
    val original: String = "",
    val amount: Double = 0.0,
    val unit: String = "",
)
