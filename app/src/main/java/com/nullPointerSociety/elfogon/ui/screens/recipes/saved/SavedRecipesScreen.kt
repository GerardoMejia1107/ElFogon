package com.nullPointerSociety.elfogon.ui.screens.recipes.saved

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nullPointerSociety.elfogon.viewmodel.UserViewModel

@Composable
fun SavedRecipesScreen(

) {
    val userViewModel: UserViewModel = viewModel(factory = UserViewModel.Factory)
    val savedRecipes = userViewModel.getListOfSavedRecipes().collectAsState()
    val savedRecipesList = savedRecipes.value


    Log.d("SavedRecipesScreen", "User ID: ${userViewModel.getListOfSavedRecipes()}")

    Column() {
        Text(
            "Aquí se mostrarán las recetas que has guardado.",
        )
        LazyColumn {
            items(savedRecipesList) { recipe ->
                Text(text = recipe.title ?: "No Title")
            }
        }
    }

}
