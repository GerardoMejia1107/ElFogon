package com.nullPointerSociety.elfogon.data.repository.impl

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.nullPointerSociety.elfogon.data.model.recipes.Recipe
import com.nullPointerSociety.elfogon.data.model.tips.Tip
import com.nullPointerSociety.elfogon.data.repository.SystemRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await

class SystemRepositoryImp(
    private val firestoreService: FirebaseFirestore
) : SystemRepository {
    private val _tips = MutableStateFlow<List<Tip>>(emptyList())
    override val tips: StateFlow<List<Tip>> = _tips

    private val _customRecipes = MutableStateFlow<List<Recipe>>(emptyList())
    override val customRecipes: StateFlow<List<Recipe>> = _customRecipes


    override suspend fun fetchTips() {
        try {
            val snapshot = firestoreService.collection("tips").get().await()
            val tipList = snapshot.documents.mapNotNull { it.toObject(Tip::class.java) }
            _tips.value = tipList
            Log.d("Show Tips", "${_tips.value}")
        } catch (e: Exception) {
            _tips.value = emptyList()
            Log.d("Show Tips", "${e.message}")
        }
    }

    override suspend fun fetchCustomRecipes() {
        try {
            val snapshot = firestoreService.collection("custom-recipes").get().await()
            val customRecipesList =
                snapshot.documents.mapNotNull { it.toObject(Recipe::class.java) }
            _customRecipes.value = customRecipesList
            Log.d("Show Custom Recipes", "${_customRecipes.value}")
        } catch (e: Exception) {
            _customRecipes.value = emptyList()
            Log.d("Show Custom Recipes", "${e.message}")
        }
    }

    override fun getRecipeById(id: Int): Recipe? {
        return customRecipes.value.find { it.id == id }
    }
}