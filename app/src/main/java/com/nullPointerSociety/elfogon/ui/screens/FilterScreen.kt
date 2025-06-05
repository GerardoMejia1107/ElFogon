package com.nullPointerSociety.elfogon.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterSection(title: String, options: List<String>) {
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
                        onClick = { /* lógica opcional */ },
                        label = { Text(option) }
                    )
                }
            }
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))
    }
}

@Composable
fun FilterScreen() {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(
            "Filtros de Búsqueda",
            style = MaterialTheme.typography.headlineSmall
        )

        FilterSection(title = "Platillos", options = listOf("De Temporada", "Platos Fuertes", "Postres", "Vegetarianos", "Comida Rápida", "Pastas"))
        FilterSection(title = "Proteína", options = listOf("Pollo", "Res", "Cerdo", "Tofu", "Pescado"))
        FilterSection(title = "Verduras", options = listOf("Zanahoria", "Brócoli", "Espinaca", "Tomate"))
        FilterSection(title = "Frutas", options = listOf("Mango", "Plátano", "Fresa"))
        FilterSection(title = "Tiempo", options = listOf("Rápido", "Moderado", "Lento"))
    }
}