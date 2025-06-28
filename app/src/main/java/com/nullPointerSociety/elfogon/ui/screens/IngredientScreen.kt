package com.nullPointerSociety.elfogon.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun IngredientScreen() {
    val ingredients = listOf(
        "Harina",
        "Frijoles Molidos",
        "Quesillo",
        "Chicharron",
        "Agua Caliente"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = "https://storage.googleapis.com/workspace-0f70711f-8b4e-4d94-86f1-2a93ccde5887/image/07f06bab-9f62-47b5-8b7c-d573844b62e1.png",
            contentDescription = "Foto de perfil circular con fondo beige claro y figura verde oscuro",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .background(Color(0xFFE6E0D8))
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Lista de Ingredientes",
            fontSize = 35.sp,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(),
            color = Color.Black
        )

        ingredients.forEach { ingredient ->
            Row(
                modifier = Modifier
                    .width(350.dp)
                    .height(50.dp)
                    .padding(vertical = 4.dp)
                    .background(Color(0xFFEADDD6), shape = MaterialTheme.shapes.medium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = ingredient,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(start = 15.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Checkbox(checked = false, onCheckedChange = null)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {},
            modifier = Modifier
                .width(200.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4A6E4D)
            )
        ) {
            Text(
                text = "Compras Finalizadas"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}



@Preview
@Composable
fun IngredientScreenPreview() {
    IngredientScreen()
}