package com.nullPointerSociety.elfogon.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextField
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material3.contentColorFor
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

data class Receta(
    val nombre: String,
    val imagenUrl: String,
    val likes: Int,
    val dislikes: Int,
    val vecesPreparada: Int
)

enum class FiltroReceta {
    MAS_LIKE, MAS_DISLIKE, MAS_PREPARADAS
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeStatsScreen(
    recetas: List<Receta>,
    onRecetaClick: (Receta) -> Unit
) {
    var busqueda by remember { mutableStateOf("") }
    var filtroSeleccionado by remember { mutableStateOf<FiltroReceta?>(null) }

    val recetasFiltradas = remember(recetas, filtroSeleccionado, busqueda) {
        recetas
            .filter { it.nombre.contains(busqueda, ignoreCase = true) }
            .let {
                when (filtroSeleccionado) {
                    FiltroReceta.MAS_LIKE -> it.sortedByDescending { it.likes }
                    FiltroReceta.MAS_DISLIKE -> it.sortedByDescending { it.dislikes }
                    FiltroReceta.MAS_PREPARADAS -> it.sortedByDescending { it.vecesPreparada }
                    null -> it
                }
            }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {

        Row(
            modifier = Modifier
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {  }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
            }
            TextField(
                value = busqueda,
                onValueChange = { busqueda = it },
                placeholder = { Text("Buscar Recetas") },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 8.dp),
                shape = RoundedCornerShape(15.dp),
                colors = TextFieldDefaults.colors(unfocusedContainerColor = MaterialTheme.colorScheme.background)
            )
        }

        Spacer(Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Button(
                onClick = {
                    filtroSeleccionado = if (filtroSeleccionado == FiltroReceta.MAS_LIKE) null else FiltroReceta.MAS_LIKE
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (filtroSeleccionado == FiltroReceta.MAS_LIKE) Color(0xFF4C5B4D) else Color.White
                ),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Text("Más Like", color = if (filtroSeleccionado == FiltroReceta.MAS_LIKE) Color.White else Color.Black)
            }
            Button(
                onClick = { filtroSeleccionado = if (filtroSeleccionado == FiltroReceta.MAS_DISLIKE) null else FiltroReceta.MAS_DISLIKE},
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (filtroSeleccionado == FiltroReceta.MAS_DISLIKE) Color(0xFF4C5B4D) else Color.White
                ),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Text("Más Dislike", color = if (filtroSeleccionado == FiltroReceta.MAS_DISLIKE) Color.White else Color.Black)
            }
            Button(
                onClick = { filtroSeleccionado = if (filtroSeleccionado == FiltroReceta.MAS_PREPARADAS) null else FiltroReceta.MAS_PREPARADAS},
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (filtroSeleccionado == FiltroReceta.MAS_PREPARADAS) Color(0xFF4C5B4D) else Color.White
                ),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Text("Más Preparadas", fontSize = 13.sp, color = if (filtroSeleccionado == FiltroReceta.MAS_PREPARADAS) Color.White else Color.Black)
            }
        }

        Spacer(Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(recetas) { receta ->
                ItemReceta(receta = receta, onClick = { onRecetaClick(receta) })
            }
        }
    }
}

@Composable
fun ItemReceta(receta: Receta, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .background(Color(0xFFEADDD6))
        ) {
            Image(
                painter = rememberImagePainter(receta.imagenUrl),
                contentDescription = "Imagen de ${receta.nombre}",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                Text(
                    receta.nombre,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.ThumbUp, contentDescription = null, tint = Color(0xFFFFC107))
                    Spacer(Modifier.width(4.dp))
                    Text("${receta.likes}")

                    Spacer(Modifier.width(12.dp))

                    Icon(Icons.Default.ThumbDown, contentDescription = null, tint = Color(0xFFD32F2F))
                    Spacer(Modifier.width(4.dp))
                    Text("${receta.dislikes}")

                    Spacer(Modifier.width(12.dp))

                    Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFF4CAF50))
                    Spacer(Modifier.width(4.dp))
                    Text("${receta.vecesPreparada}")
                }
            }
        }
    }
}

@Preview
@Composable
fun RecipeStatsPreview() {
    val recetaData = listOf(
        Receta("Pizza","",25,115,135),
        Receta("Taco","",35,100,235),
        Receta("Hamburgues","",15,105,335),
        Receta("Sushi","",50,200,435),
        Receta("Pasta","",65,75,535)
    )
    RecipeStatsScreen(recetaData) { }
}