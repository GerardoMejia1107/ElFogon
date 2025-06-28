package com.nullPointerSociety.elfogon.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nullPointerSociety.elfogon.data.repository.impl.AuthState
import com.nullPointerSociety.elfogon.ui.components.RecipeCard
import com.nullPointerSociety.elfogon.ui.components.SearchBar
import com.nullPointerSociety.elfogon.ui.components.SystemRecipeCard
import com.nullPointerSociety.elfogon.ui.components.TipModal
import com.nullPointerSociety.elfogon.ui.navigation.LogInScreenNav

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(),
    onNavigateToFilters: () -> Unit,
    onRecipeClick: (Int) -> Unit = {},
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    navController: NavController
) {
    val searchResults by homeViewModel.searchResults.collectAsState()
    val categorizedRecipes by homeViewModel.categorizedRecipes.collectAsState()
    val authState by homeViewModel.authState.collectAsState()
    val tips by homeViewModel.tips.collectAsState()

    val customRecipes by homeViewModel.customRecipes.collectAsState()

    val hasShownTip by homeViewModel.hasShownTip.collectAsState()

    LaunchedEffect(authState) {
        if (authState is AuthState.Unauthenticated) {
            navController.navigate(LogInScreenNav)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        // Tip modal
        if (!hasShownTip && tips.isNotEmpty()) {
            TipModal(
                tipData = tips.random(),
                onDismiss = { homeViewModel.markTipAsShown() }
            )
        }

        // Search bar
        SearchBar(
            onFilterClick = onNavigateToFilters,
            onBackClick = { /* sin acción */ },
            onQueryChange = { query ->
                homeViewModel.filterRecipesByQuery(query)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Subtitle
        Surface(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Descubre recetas deliciosas y fáciles de preparar",
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 1) show search results grid
        if (searchResults.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                userScrollEnabled = true,
                contentPadding = PaddingValues(bottom = 140.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(searchResults) { recipe ->
                    RecipeCard(recipe, onRecipeClick)
                }
            }

            // 2) else show categorized lists
        } else if (categorizedRecipes.isNotEmpty()) {
            LazyColumn(
                contentPadding = PaddingValues(bottom = 140.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Sección de recetas personalizadas
                if (customRecipes.isNotEmpty()) {
                    item {
                        Text(
                            text = "Tus Recetas Especiales 🍽️",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                    item {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(customRecipes) { recipe ->
                                SystemRecipeCard(recipe, onRecipeClick)
                            }
                        }
                    }
                }

                items(categorizedRecipes) { group ->
                    Text(
                        text = group.tag,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(group.recipes) { recipe ->
                            RecipeCard(recipe, onRecipeClick)
                        }
                    }
                }
            }

            // 3) else show loading spinner
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
    }
}
