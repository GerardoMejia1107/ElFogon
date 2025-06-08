package com.nullPointerSociety.elfogon.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nullPointerSociety.elfogon.data.model.Recipe
import com.nullPointerSociety.elfogon.ui.components.RecipeCard
import com.nullPointerSociety.elfogon.ui.components.SearchBar
import com.nullPointerSociety.elfogon.ui.layout.CustomTopBar
import com.nullPointerSociety.elfogon.viewmodel.SpooncularViewModel

val dummyRecipes = listOf(
    Recipe(
        id = 664932,
        title = "Walnut Pesto",
        image = "https://img.spoonacular.com/recipes/664932-556x370.jpg",
        readyInMinutes = 45,
        servings = 12
    ),
    Recipe(
        id = 649403,
        title = "Lebanese Kibbeh",
        image = "https://img.spoonacular.com/recipes/649403-556x370.jpg",
        readyInMinutes = 45,
        servings = 6
    ),
    Recipe(
        id = 637297,
        title = "Cauliflower Chickpea Stew",
        image = "https://img.spoonacular.com/recipes/637297-556x370.",
        readyInMinutes = 45,
        servings = 4
    ),
    Recipe(
        id = 636732,
        title = "Cajun Lobster Pasta",
        image = "https://img.spoonacular.com/recipes/636732-556x370.jpg",
        readyInMinutes = 45,
        servings = 1
    ),
    Recipe(
        id = 647395,
        title = "Hot Artichoke Crab Dip",
        image = "https://img.spoonacular.com/recipes/647395-556x370.jpg",
        readyInMinutes = 45,
        servings = 4
    )
)

@Composable
fun HomeScreen(
    onNavigateToFilters: () -> Unit,
    viewModel: SpooncularViewModel = viewModel()
) {
    val sampleRecipes = viewModel.recipes.collectAsState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopBar(title = "DEL FOGON")
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            SearchBar(
                onFilterClick = onNavigateToFilters,
                onBackClick = { /* sin acción por ahora */ }
            )

            Spacer(modifier = Modifier.height(16.dp))
            Surface(
                modifier = Modifier.fillMaxWidth()

            ) {
                Text(
                    text = "Descubre recetas deliciosas y fáciles de preparar",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                )
            }


            Spacer(modifier = Modifier.height(16.dp))

            //sampleRecipes.value.isEmpty()
            if (dummyRecipes.isEmpty()) {
                CircularProgressIndicator()
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(bottom = 140.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement   = Arrangement.spacedBy(16.dp)
                ) {
                    items(dummyRecipes) { RecipeCard(it, {}) }
                }

            }


        }
    }
}
