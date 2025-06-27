package com.nullPointerSociety.elfogon.ui.components.recipeDetailsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nullPointerSociety.elfogon.data.model.recipes.Instructions

@Composable
fun Steps(instructions: List<Instructions>) {
    instructions.forEach { instruction ->
        instruction.steps.forEach { step ->
            Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)) {
                Text(
                    text = "Step ${step.number}: ${step.step}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
