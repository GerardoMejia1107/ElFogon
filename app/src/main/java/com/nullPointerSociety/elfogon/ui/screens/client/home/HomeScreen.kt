package com.nullPointerSociety.elfogon.ui.screens.client.home

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
    onRecipeClick: (String) -> Unit = {},
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    navController: NavController
) {
    val searchResults by homeViewModel.searchResults.collectAsState()
    val categorizedRecipes by homeViewModel.categorizedRecipes.collectAsState()
    val customRecipes by homeViewModel.customRecipes.collectAsState()
    val tips by homeViewModel.tips.collectAsState()
    val hasShownTip by homeViewModel.hasShownTip.collectAsState()
    val authState by homeViewModel.authState.collectAsState()

    val currentMonth = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH) + 1

    val seasonRecipes = customRecipes.filter { recipe ->
        recipe.months.contains(currentMonth)
    }

    LaunchedEffect(authState) {
        if (authState is AuthState.Unauthenticated) {
            navController.navigate(LogInScreenNav)
        }
    }


    if (!hasShownTip && tips.isNotEmpty()) {
        TipModal(
            tipData = tips.random(),
            onDismiss = { homeViewModel.markTipAsShown() }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        SearchBar(
            onFilterClick = onNavigateToFilters,
            onBackClick = { /* no-op */ },
            onQueryChange = { query -> homeViewModel.filterRecipesByQuery(query) }
        )
        Spacer(modifier = Modifier.height(8.dp))


        Box(modifier = Modifier.weight(1f)) {
            when {

                searchResults.isNotEmpty() -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 140.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(searchResults) { recipe ->
                            RecipeCard(recipe, onRecipeClick)
                        }
                    }
                }


                categorizedRecipes.isNotEmpty() -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 140.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Subtitle
                        item {
                            Surface(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = "Find your next meal 🍽️",
                                    style = MaterialTheme.typography.displaySmall,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(MaterialTheme.colorScheme.background)
                                        .padding(vertical = 8.dp)
                                )
                            }
                        }


                        if (customRecipes.isNotEmpty()) {
                            item {
                                Text(
                                    text = "Checkout our selection for this month! 🍳",
                                    style = MaterialTheme.typography.titleLarge
                                )
                            }
                            item {
                                LazyRow(
                                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    items(seasonRecipes) { recipe ->
                                        SystemRecipeCard(recipe, onRecipeClick)
                                    }
                                }
                            }
                        }


                        categorizedRecipes.forEach { group ->
                            item {
                                Text(
                                    text = group.tag,
                                    style = MaterialTheme.typography.titleLarge
                                )
                            }
                            item {
                                LazyRow(
                                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    items(group.recipes) { recipe ->
                                        RecipeCard(recipe, onClick = onRecipeClick)
                                    }
                                }
                            }
                        }

                    }
                }


                else -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}
