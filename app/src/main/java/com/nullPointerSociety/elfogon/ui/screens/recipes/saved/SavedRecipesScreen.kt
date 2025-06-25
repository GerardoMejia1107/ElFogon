package com.nullPointerSociety.elfogon.ui.screens.recipes.saved

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nullPointerSociety.elfogon.ui.components.savedRecipes.SavedCard
import com.nullPointerSociety.elfogon.viewmodel.UserViewModel

@Composable
fun SavedRecipesScreen(
    onRecipeClick: (Int) -> Unit = {},
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
) {
    val userViewModel: UserViewModel = viewModel(factory = UserViewModel.Factory)
    val savedRecipes = userViewModel.getListOfSavedRecipes().collectAsState()
    val savedRecipesList = savedRecipes.value


    Log.d("SavedRecipesScreen", "User ID: ${userViewModel.getListOfSavedRecipes()}")

    Column(modifier = modifier.fillMaxSize()) {
        Text(
            "Aquí se mostrarán las recetas que has guardado.",
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(savedRecipesList) {
                SavedCard(it, onViewClick = onRecipeClick) {}
            }
        }
    }

}
