package com.nullPointerSociety.elfogon.ui.components.recipeDetailsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nullPointerSociety.elfogon.data.model.recipes.ExtendedIngredient

@Composable
fun Ingredients(ingredients: List<ExtendedIngredient>) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Text("Lista de Ingredientes", style = MaterialTheme.typography.titleLarge)
        ingredients.forEach {
            Column {
                /*Text(
                    text = it.nameClean.orEmpty(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 7.dp)
                )*/
                Row(
                    modifier = Modifier
                        .width(350.dp)
                        .height(50.dp)
                        .padding(vertical = 4.dp)
                        .background(Color(0xFFEADDD6), shape = MaterialTheme.shapes.medium),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = it.nameClean.orEmpty(),
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(start = 15.dp, top = 7.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Checkbox(checked = false, onCheckedChange = null)
                }
            }

        }
    }
}
