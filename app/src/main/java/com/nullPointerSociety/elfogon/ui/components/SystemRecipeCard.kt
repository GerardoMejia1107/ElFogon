package com.nullPointerSociety.elfogon.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.nullPointerSociety.elfogon.data.model.recipes.Recipe

@Composable
fun SystemRecipeCard(
    recipeSystem: Recipe,
    onClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .width(200.dp) // ✅ Uniforme en LazyRow y Grid
            .height(280.dp)
            .clickable(onClick = { onClick(recipeSystem.id) }),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f) // ✅ Proporción uniforme
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(recipeSystem.image.orEmpty()) // ✅ evita null
                            .crossfade(true)
                            .scale(Scale.FILL)
                            .build(),
                        contentScale = ContentScale.Crop
                    ),
                    contentDescription = recipeSystem.title,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                )

                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Favorito",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(20.dp)
                )
            }

            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = recipeSystem.title.orEmpty(),
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Porciones: ${recipeSystem.servings ?: "-"}",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "⏱ ${recipeSystem.readyInMinutes ?: "-"} min",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
