package com.nullPointerSociety.elfogon.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nullPointerSociety.elfogon.data.model.Recipe
import com.nullPointerSociety.elfogon.ui.components.RecipeCard
import com.nullPointerSociety.elfogon.ui.components.SearchBar
import com.nullPointerSociety.elfogon.ui.layout.CustomTopBar
import com.nullPointerSociety.elfogon.ui.theme.AppBackground


@Composable
fun HomeScreen(onNavigateToFilters: () -> Unit) {
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
        containerColor = AppBackground,
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
                        .padding(horizontal = 16.dp)
                        .background(AppBackground)
                )
            }


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