package com.nullPointerSociety.elfogon.data.api

import com.nullPointerSociety.elfogon.data.model.Recipe
import com.nullPointerSociety.elfogon.data.wrapper.SpooncularRecipeWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface SpooncularService {
    @GET("recipes/random")
    suspend fun getRecipes(
        @Query("apiKey") apiKey: String,
        @Query("number") number: Int = 100
    ): SpooncularRecipeWrapper
}
