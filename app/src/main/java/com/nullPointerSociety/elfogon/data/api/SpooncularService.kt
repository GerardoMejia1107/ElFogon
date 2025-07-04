package com.nullPointerSociety.elfogon.data.api

import com.nullPointerSociety.elfogon.data.model.recipes.Recipe
import com.nullPointerSociety.elfogon.data.wrapper.SpooncularResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface SpooncularService {
    @GET("recipes/random")
    suspend fun getRecipes(
        @Header("x-api-key") token: String,
        @Query("number") number: Int = 100,
        @Query("tags") tags: String? = null // ✅ NUEVO: filtro por categoría
    ): SpooncularResponse<Recipe>

    @GET("recipes/informationBulk")
    suspend fun getRecipeByIdInfo(
        @Header("x-api-key") token: String,
        @Query("ids") ids: String
    ): List<Recipe>

    @GET("recipes/information")
    suspend fun getRecipeInfoById(
        @Header("x-api-key") token: String,
        @Path("id") id: Int
    ): Recipe
}

