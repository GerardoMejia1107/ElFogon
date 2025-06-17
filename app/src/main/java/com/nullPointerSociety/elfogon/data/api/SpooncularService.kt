package com.nullPointerSociety.elfogon.data.api

import com.nullPointerSociety.elfogon.data.model.RecipeApi
import com.nullPointerSociety.elfogon.data.wrapper.SpooncularResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SpooncularService {
    @GET("recipes/random")
    suspend fun getRecipes(
        @Header("x-api-key") token: String,
        @Query("number") number: Int = 100,
        @Query("tags") tags: String? = null // ✅ NUEVO: filtro por categoría
    ): SpooncularResponse<RecipeApi>
}

