package com.nullPointerSociety.elfogon.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Recipe(
    val id: Int,
    val title: String,
    val imageRes: Int,
    val likes: Int,
    val dislikes: Int,
    val prepared: Int,
    var selected: Boolean = false
)



@Composable
fun RecipesStatsScreen() {
    var searchText by remember { mutableStateOf("") }
    var filter by remember { mutableStateOf(FilterType.None) }
    var recipes by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        TopBar(searchText) { newText -> searchText = newText }
        Spacer(modifier = Modifier.height(8.dp))
        FilterButtons(filter) { filter = it }
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            val filteredRecipes = recipes.filter {
                it.title.contains(searchText, ignoreCase = true)
            }.let {
                when (filter) {
                    FilterType.None -> it
                    FilterType.MostLiked -> it.sortedByDescending { r -> r.likes }
                    FilterType.MostDisliked -> it.sortedByDescending { r -> r.dislikes }
                    FilterType.MostPrepared -> it.sortedByDescending { r -> r.prepared }
                }
            }
            items(filteredRecipes) { recipe ->
                RecipeRow(recipe) {
                    // Toggle selected state
                    recipes = recipes.map {
                        if (it.id == recipe.id) it.copy(selected = !it.selected) else it
                    }
                }
            }
        }
    }
}

enum class FilterType {
    None, MostLiked, MostDisliked, MostPrepared
}

@Composable
fun TopBar(searchText: String, onSearchChange: (String) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = { /* Handle Back */ }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back"
            )
        }
        OutlinedTextField(
            value = searchText,
            onValueChange = onSearchChange,
            placeholder = { Text("Buscar Recetas") },
            modifier = Modifier
                .weight(1f)
                .height(48.dp),
            singleLine = true,
            shape = RoundedCornerShape(24.dp)
        )
    }
}

@Composable
fun FilterButtons(currentFilter: FilterType, onFilterSelected: (FilterType) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilterButton(
            title = "Más Like",
            selected = currentFilter == FilterType.MostLiked,
            onClick = { onFilterSelected(FilterType.MostLiked) }
        )
        FilterButton(
            title = "Más Dislike",
            selected = currentFilter == FilterType.MostDisliked,
            onClick = { onFilterSelected(FilterType.MostDisliked) }
        )
        FilterButton(
            title = "Más Preparadas",
            selected = currentFilter == FilterType.MostPrepared,
            onClick = { onFilterSelected(FilterType.MostPrepared) }
        )
    }
}

@Composable
fun FilterButton(title: String, selected: Boolean, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(24.dp),
        border = if (selected) BorderStroke(2.dp, MaterialTheme.colorScheme.primary) else null,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = if (selected) MaterialTheme.colorScheme.primary else Color.Black
        )
    ) {
        Text(title)
    }
}

@Composable
fun RecipeRow(recipe: Recipe, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = recipe.imageRes),
                contentDescription = recipe.title,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = recipe.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 2
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Checkbox(
                    checked = recipe.selected,
                    onCheckedChange = { onClick() }
                )
                StatRow(
                    icon = Icons.Default.ThumbUp,
                    count = recipe.likes,
                    iconTint = Color(0xFFFFC107) // Amber/yellow
                )
                StatRow(
                    icon = Icons.Default.ThumbUp,
                    count = recipe.dislikes,
                    iconTint = Color(0xFFE53935) // Red
                )
                Text(
                    text = recipe.prepared.toString(),
                    modifier = Modifier.padding(top = 4.dp),
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun StatRow(icon: ImageVector, count: Int, iconTint: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 2.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(count.toString())
    }
}

@Preview
@Composable
fun RecipesStatsPreview() {
    RecipesStatsScreen()
}