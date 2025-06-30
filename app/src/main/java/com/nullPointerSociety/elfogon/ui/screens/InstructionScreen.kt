package com.nullPointerSociety.elfogon.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InstructionScreen() {
    var pasoActual by remember { mutableStateOf(0) }
    val pasos = listOf(
        "En un recipiente colocar...",
        "Luego añadir un poco de....",
        "Precaliente el sarten con...",
        "Sirva y acompañelo de..."
    )

    Instructions(
        pasos = pasos,
        pasoActual = pasoActual,
        onSiguientePaso = {
            if (pasoActual < pasos.size - 1) pasoActual++
        },
        onFinalizar = {
            println("Receta finalizada")
        }
    )
}

@Composable
fun Instructions(
    pasos: List<String>,
    pasoActual: Int,
    onSiguientePaso: () -> Unit,
    onFinalizar: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Card(
                modifier = Modifier
                    .width(110.dp)
                    .height(40.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 15.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(20.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFEADDD6)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Paso ${pasoActual + 1} de ${pasos.size}",
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        LinearProgressIndicator(
            progress = (pasoActual + 1).toFloat() / pasos.size.toFloat(),
            modifier = Modifier
                .width(360.dp)
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = Color(0xFF4C5B4D),
            trackColor = Color(0xFFE0E0E0)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier.width(300.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 15.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFEADDD6)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        pasos[pasoActual],
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(24.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (pasoActual < pasos.size - 1) {
                Button(
                    onClick = onSiguientePaso,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4C5B4D)),
                    shape = RoundedCornerShape(50)
                ) {
                    Text("Siguiente Paso", color = Color.White)
                }
            } else {
                Button(
                    onClick = onFinalizar,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4C5B4D)),
                    shape = RoundedCornerShape(50)
                ) {
                    Text("Finalizar", color = Color.White)
                }
            }
        }
    }
}

@Preview
@Composable
fun InstructionScreenView() {
    InstructionScreen()
}