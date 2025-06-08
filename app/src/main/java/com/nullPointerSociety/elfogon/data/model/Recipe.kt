package com.nullPointerSociety.elfogon.data.model

data class Recipe(
    val id: Int,
    val title: String?,
    val image: String?,
    val readyInMinutes: Int?,
    val servings: Int?,
    val summary: String?
)