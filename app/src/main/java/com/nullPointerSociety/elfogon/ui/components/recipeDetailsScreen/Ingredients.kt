package com.nullPointerSociety.elfogon.ui.components.recipeDetailsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
        Text(
            text = "List of ingredients you need for this!",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 12.dp)
        )

        ingredients.forEach {
            Surface(
                tonalElevation = 2.dp,
                shadowElevation = 4.dp,
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFFFDFDFD),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "•",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(end = 8.dp),
                        color = Color(0xFF4a6e4d)
                    )
                    Text(
                        text = it.nameClean.orEmpty().replaceFirstChar { c -> c.uppercase() },
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 18.sp,
                        color =Color.Black
                    )
                }
            }
        }
    }
}
