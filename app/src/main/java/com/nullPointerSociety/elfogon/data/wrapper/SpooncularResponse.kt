package com.nullPointerSociety.elfogon.data.wrapper

import com.google.gson.annotations.SerializedName
import com.nullPointerSociety.elfogon.data.model.ExtendedIngredient
import com.nullPointerSociety.elfogon.data.model.Instructions
import com.nullPointerSociety.elfogon.data.model.ShortIngredient
import com.nullPointerSociety.elfogon.data.model.Step

data class SpooncularResponse<T>(
    @SerializedName("page")
    val page: Int,
    @SerializedName("recipes")
    val recipes: List<T>,
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("extendedIngredients")
    val extendedIngredients: List<ExtendedIngredient>,
    @SerializedName("steps")
    val steps: List<Step>,

)
