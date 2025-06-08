package com.nullPointerSociety.elfogon.data.wrapper

import com.google.gson.annotations.SerializedName

data class SpooncularResponse<T>(
    @SerializedName("page")
    val page: Int,
    @SerializedName("recipes")
    val recipes: List<T>,
    @SerializedName("totalResults")
    val totalResults: Int

)
