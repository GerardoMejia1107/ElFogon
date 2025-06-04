package com.nullPointerSociety.elfogon.ui.screen.search

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import com.nullPointerSociety.elfogon.model.Recipe
import com.nullPointerSociety.elfogon.ui.components.BottomNavigationBar
import com.nullPointerSociety.elfogon.ui.components.RecipeCard
import androidx.compose.foundation.lazy.items
import com.nullPointerSociety.elfogon.ui.components.SearchBar


@Composable
fun SearchScreen(onNavigateToFilters: () -> Unit) {
    val sampleRecipes = listOf(
        Recipe(
            name = "Hamburguesa",
            category = "de Res",
            imageUrl = "https://www.cocinavital.mx/wp-content/uploads/2017/08/hamburguesa-res.jpeg",
            time = 45
        ),
        Recipe(
            name = "Tacos",
            category = "de Res",
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSEyCMoWDQibIAbAOrdGghPN3df0ItePuj80A&s",
            time = 25
        )
    )

    Scaffold(
        bottomBar = { BottomNavigationBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SearchBar(
                onFilterClick = onNavigateToFilters,
                onBackClick = { /* sin acción por ahora */ }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Resultados:", style = MaterialTheme.typography.titleMedium)

            LazyRow {
                items(sampleRecipes) { recipe ->
                    RecipeCard(recipe = recipe, onClick = { })
                }
            }
        }
    }
}