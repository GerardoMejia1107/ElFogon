package com.nullPointerSociety.elfogon.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.nullPointerSociety.elfogon.viewmodel.AuthViewModel

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel,
    navController: NavController,
    scrollState: LazyListState
) {

    var expandPassword by remember { mutableStateOf(false) }
    var expandEmail by remember { mutableStateOf(false) }
    val newPass = remember { mutableStateOf("") }
    val confirmPass = remember { mutableStateOf("") }
    val newEmail = remember { mutableStateOf("") }

    LazyColumn(
        state = scrollState,
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        item {
            Spacer(modifier = Modifier.height(32.dp))

            Box(
                modifier = Modifier
                    .size(250.dp)
                    .background(Color(0xFF7B9A85), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = "https://storage.googleapis.com/workspace-0f70711f-8b4e-4d94-86f1-2a93ccde5887/image/07f06bab-9f62-47b5-8b7c-d573844b62e1.png",
                    contentDescription = "Foto de perfil circular con fondo beige claro y figura verde oscuro",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(250.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE6E0D8))
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "José Armando Cardoza Sandoval",
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                lineHeight = 45.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "0000@gmail.com",
                fontSize = 24.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .background(Color(0xFF4A6E4D), shape = MaterialTheme.shapes.medium)
                        .padding(16.dp)
                        .clickable {}
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "25", fontSize = 30.sp, color = Color.White)
                        Text(
                            text = "Recetas Guardadas",
                            fontSize = 16.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .background(Color(0xFF4A6E4D), shape = MaterialTheme.shapes.medium)
                        .padding(16.dp)
                        .clickable {}
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "25", fontSize = 30.sp, color = Color.White)
                        Text(
                            text = "Recetas Preparadas",
                            fontSize = 16.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            ExpandableSection(
                title = "Actualizar Contraseña",
                expanded = expandPassword,
                onToggle = { expandPassword = !expandPassword }
            ) {
                TextField(
                    value = newPass.value,
                    onValueChange = { newPass.value = it },
                    label = { Text("Contraseña Nueva") },
                    modifier = Modifier
                        .width(320.dp)
                        .height(50.dp)
                        .background(
                            MaterialTheme.colorScheme.background
                        )
                )

                TextField(
                    value = confirmPass.value,
                    onValueChange = { newPass.value = it },
                    label = { Text("Confirmar Contraseña") },
                    modifier = Modifier
                        .width(320.dp)
                        .height(50.dp)
                        .background(
                            MaterialTheme.colorScheme.background
                        )
                )

                Button(
                    onClick = { },
                    modifier = Modifier
                        .width(200.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4A6E4D)
                    )
                ) {
                    Text(text = "Cambiar Contraseña", fontSize = 20.sp)
                }
            }

            Divider(
                color = Color.Black, modifier = Modifier
                    .width(350.dp)
                    .height(2.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            ExpandableSection(
                title = "Actualizar Correo",
                expanded = expandEmail,
                onToggle = { expandEmail = !expandEmail }
            ) {
                TextField(
                    value = newEmail.value,
                    onValueChange = { newEmail.value = it },
                    label = { Text("Correo Nuevo") },
                    modifier = Modifier
                        .width(320.dp)
                        .height(50.dp)
                        .background(
                            MaterialTheme.colorScheme.background
                        )
                )

                Button(
                    onClick = { },
                    modifier = Modifier
                        .width(200.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4A6E4D)
                    )
                ) {
                    Text(text = "Cambiar Correo", fontSize = 20.sp)
                }
            }

            Divider(
                color = Color.Black, modifier = Modifier
                    .width(350.dp)
                    .height(2.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Botón cerrar sesión
            Button(
                onClick = { },
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4A6E4D)
                )
            ) {
                Text(text = "Cerrar Sesión", fontSize = 20.sp)
            }
        }
    }
}

@Composable
fun ExpandableSection(
    title: String,
    expanded: Boolean,
    onToggle: () -> Unit,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .width(350.dp)
            .clickable { onToggle() }
            .padding(vertical = 12.dp)
            .animateContentSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = if (expanded) "Contraer $title" else "Expandir $title"
            )
        }

        if (expanded) {
            Spacer(modifier = Modifier.height(8.dp))
            content()
        }
    }
}

