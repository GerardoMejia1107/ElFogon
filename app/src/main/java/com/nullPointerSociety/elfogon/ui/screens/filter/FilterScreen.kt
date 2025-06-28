package com.nullPointerSociety.elfogon.ui.screens.filter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nullPointerSociety.elfogon.ui.components.RecipeCard
import com.nullPointerSociety.elfogon.viewmodel.SpooncularViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterSection(
    title: String,
    options: List<String>,
    onCategorySelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
        )

        if (expanded) {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(top = 8.dp)
            ) {
                options.forEach { option ->
                    AssistChip(
                        onClick = { onCategorySelected(option) },
                        label = { Text(option) }
                    )
                }
            }
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
    }
}

@Composable
fun FilterScreen(viewModel: SpooncularViewModel = viewModel()) {
    val recipes by viewModel.recipes.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            "Filtros de Búsqueda",
            style = MaterialTheme.typography.headlineSmall
        )

        FilterSection(
            title = "Platillos",
            options = listOf("vegetarian", "dessert", "pasta", "main course", "side dish", "quick"),
            onCategorySelected = {  }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Resultados", style = MaterialTheme.typography.titleMedium)

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(recipes) { recipe ->
                RecipeCard(recipeApiSpoon = recipe) {
                    // Opcional: lógica para ir al detalle
                    // navController.navigate("recipe_detail/$it")
                }
            }
        }
    }
}

