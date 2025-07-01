package com.nullPointerSociety.elfogon.data.repository.impl

import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.nullPointerSociety.elfogon.data.model.recipes.Recipe
import com.nullPointerSociety.elfogon.data.model.user.UserData
import com.nullPointerSociety.elfogon.data.repository.UserRepository
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl(
    private val firestoreService: FirebaseFirestore
) : UserRepository {
    override suspend fun getUserData(uid: String): UserData? {
        val snapshot = firestoreService.collection("users").document(uid).get().await()
        if (snapshot.exists()) {
            Log.d("UserRepositoryImpl", "getUserData: ${snapshot.getString("name")}")
        }
        return if (snapshot.exists()) {
            UserData(
                email = snapshot.getString("email") ?: "",
                name = snapshot.getString("name") ?: "",
                lastName = snapshot.getString("lastName") ?: "",
                profilePictureUrl = snapshot.getString("profilePictureUrl") ?: ""
            )
        } else null
    }

    override suspend fun fetchUserData(uid: String): UserData? {
        return getUserData(uid)
    }

    override suspend fun updateSavedRecipes(uid: String?, recipeId: String) {
        val updateMap = mapOf("savedRecipes" to FieldValue.arrayUnion(recipeId))
        firestoreService.collection("users").document(uid ?: "").set(updateMap, SetOptions.merge())
            .await()

    }

    override suspend fun updateCustomSavedRecipes(uid: String?, recipeId: String) {
        val updateMap = mapOf("customSavedRecipes" to FieldValue.arrayUnion(recipeId))
        firestoreService.collection("users").document(uid ?: "").set(updateMap, SetOptions.merge())
            .await()

    }

    override suspend fun getSavedRecipes(uid: String): List<String> {
        val snapshot = firestoreService.collection("users").document(uid).get().await()
        if (snapshot.exists()) {
            Log.d("UserRepositoryImpl", "getSavedRecipes: ${snapshot.get("savedRecipes")}")
        }
        return snapshot.get("savedRecipes") as? List<String> ?: emptyList()
    }

    override suspend fun getCustomSavedRecipes(uid: String): List<Recipe> {
        return try {
            val snapshot = firestoreService.collection("users").document(uid).get().await()

            val ids = snapshot.get("customSavedRecipes") as? List<String> ?: emptyList()

            if (ids.isEmpty()) return emptyList()

            val recipes = ids.mapNotNull { id ->
                firestoreService.collection("custom-recipes")
                    .document(id)
                    .get()
                    .await()
                    .toObject(Recipe::class.java)
            }

            recipes
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }


    override suspend fun saveUserData(
        uid: String,
        userData: UserData
    ) {
        val doc = mapOf(
            "role" to userData.role,
            "email" to userData.email,
            "name" to userData.name,
            "lastName" to userData.lastName,
            "profilePictureUrl" to userData.profilePictureUrl,
            "savedRecipes" to listOf<String>(),
            "madeRecipes" to listOf<String>(),
            "customSavedRecipes" to listOf<String>(),
        )
        firestoreService.collection("users").document(uid).set(doc).await()
    }
}