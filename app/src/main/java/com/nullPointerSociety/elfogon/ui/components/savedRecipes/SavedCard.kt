package com.nullPointerSociety.elfogon.ui.components.savedRecipes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nullPointerSociety.elfogon.data.model.RecipeApi


@Composable
fun SavedCard(
    recipe: RecipeApi,
    onViewClick: (Int) -> Unit,
    onDeleteClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clickable(onClick = { onViewClick(recipe.id.toInt()) }),
        shape = RoundedCornerShape(16.dp),

        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = recipe.image,
                contentDescription = recipe.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp))
            )

            Box(
                modifier = Modifier
                    .weight(2f)
                    .padding(12.dp)
            ) {

                Column(modifier = Modifier.align(Alignment.CenterStart)) {
                    Text(
                        text = recipe.title ?: "No Title",
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Porciones: ${recipe.servings}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "⏱ ${recipe.readyInMinutes} min",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar receta",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                        .size(25.dp)
                        .clickable { onDeleteClick(recipe.id) }
                )
            }
        }
    }
}


