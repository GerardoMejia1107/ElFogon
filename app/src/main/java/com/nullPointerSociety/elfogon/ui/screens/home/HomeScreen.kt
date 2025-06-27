package com.nullPointerSociety.elfogon.ui.screens.home

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nullPointerSociety.elfogon.data.repository.impl.AuthState
import com.nullPointerSociety.elfogon.ui.components.RecipeCard
import com.nullPointerSociety.elfogon.ui.components.SearchBar
import com.nullPointerSociety.elfogon.ui.navigation.LogInScreenNav


@Composable
fun HomeScreen(
    onNavigateToFilters: () -> Unit,

    onRecipeClick: (Int) -> Unit = {},
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    navController: NavController,

    ) {
    val homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)
    val sampleRecipes = homeViewModel.searchResults.collectAsState()
    val auth = homeViewModel.authState.collectAsState()
    val tips = homeViewModel.tips.collectAsState()

    LaunchedEffect(auth.value) {
        when (auth.value) {
            is AuthState.Unauthenticated -> navController.navigate(LogInScreenNav)
            else -> Unit
        }
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        SearchBar(
            onFilterClick = onNavigateToFilters,
            onBackClick = { /* sin acción */ },
            onQueryChange = { query ->
                homeViewModel.filterRecipesByQuery(query)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Surface(
            modifier = Modifier.fillMaxWidth()

        ) {
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

        //sampleRecipes.value.isEmpty()
        if (sampleRecipes.value.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CircularProgressIndicator()
            }

        } else
            LazyVerticalGrid(
                userScrollEnabled = true,
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(bottom = 140.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(sampleRecipes.value) { RecipeCard(it, onRecipeClick) }
            }

    }


}


