package com.nullPointerSociety.elfogon.data.model.recipes

data class ExtendedIngredient(
    val id: Int,
    val nameClean: String?,
    val original: String?,
    val amount: Double?,
    val unit: String?,
)
