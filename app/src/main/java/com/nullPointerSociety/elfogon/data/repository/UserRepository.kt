package com.nullPointerSociety.elfogon.data.repository

import com.nullPointerSociety.elfogon.data.model.user.UserData

interface UserRepository {
    suspend fun getUserData(uid: String): UserData?
    suspend fun saveUserData(uid: String, userData: UserData)
    suspend fun fetchUserData(uid: String): UserData?

    //Para agregar recetas a guardadas
    suspend fun updateSavedRecipes(
        uid: String?,
        recipeId: String
    )

    suspend fun getSavedRecipes(uid: String): List<String>

    /*
    *  suspend fun updateMadeRecipes(
         uid: String,
         recipeId: String
     )

     suspend fun getSavedRecipes(uid: String): List<String>
     suspend fun getMadeRecipes(uid: String): List<String>
     suspend fun deleteSavedRecipe(
         uid: String,
         recipeId: String
     )

     suspend fun deleteMadeRecipe(
         uid: String,
         recipeId: String
     )
    * */

}