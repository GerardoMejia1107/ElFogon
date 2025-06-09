package com.nullPointerSociety.elfogon.ui.components.recipeDetailsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RecipeDetailsTitle(title: String?, readyInMinutes: Int?) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Text(
            text = title.orEmpty(),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Ready in ${readyInMinutes ?: 0} minutes",
            style = MaterialTheme.typography.titleSmall
        )
    }
}