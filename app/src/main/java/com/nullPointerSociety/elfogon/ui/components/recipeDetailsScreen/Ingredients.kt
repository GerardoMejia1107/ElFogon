package com.nullPointerSociety.elfogon.ui.components.recipeDetailsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nullPointerSociety.elfogon.data.model.recipes.ExtendedIngredient

@Composable
fun Ingredients(ingredients: List<ExtendedIngredient>) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Text("Ingredients You Need!", style = MaterialTheme.typography.titleLarge)
        ingredients.forEach {
            Column {
                Text(
                    text = it.nameClean.orEmpty(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 7.dp)
                )

            }

        }
    }
}
