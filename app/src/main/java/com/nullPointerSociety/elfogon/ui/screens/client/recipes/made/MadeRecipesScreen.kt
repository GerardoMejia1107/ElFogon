package com.nullPointerSociety.elfogon.ui.screens.client.recipes.made

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nullPointerSociety.elfogon.ui.components.savedRecipes.SavedCard
import com.nullPointerSociety.elfogon.ui.screens.client.recipes.saved.SavedRecipesViewModel

@Composable
fun MadeRecipesScreen(
    onRecipeClick: (String) -> Unit = {},
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
) {
    val madeRecipesViewModel: MadeRecipesViewModel =
        viewModel(factory = MadeRecipesViewModel.Factory)

    val madeRecipesList = madeRecipesViewModel.getListOfMadeRecipes().collectAsState().value

    val isLoading = madeRecipesList.isEmpty()

    if (isLoading) {
        // Muestra una pantalla de carga mientras se cargan las recetas hechas
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        // Muestra la lista de recetas hechas cuando ya están disponibles
        Column(modifier = modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(madeRecipesList) { recipe ->
                    Log.d("MadeRecipesScreen", "Recipe: $recipe")
                    SavedCard(
                        recipe,
                        onViewClick = onRecipeClick
                    ) {}
                }
            }
        }
    }
}
