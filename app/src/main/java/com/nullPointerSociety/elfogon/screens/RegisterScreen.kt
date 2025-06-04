package com.nullPointerSociety.elfogon.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen() {
    val name = remember { mutableStateOf("") }
    val lastname = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val pass = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color(0xFFFFFBF7)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "DEL FOGÓN",
                fontSize = 32.sp,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(24.dp))

            TextField(
                value = name.value,
                onValueChange = { name.value = it },
                label = { Text("Nombres") },
                colors = TextFieldDefaults.colors(
                    Color.White
                ),
                modifier = Modifier
                    .width(320.dp)
                    .height(50.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = lastname.value,
                onValueChange = { lastname.value = it },
                label = { Text("Apellidos") },
                colors = TextFieldDefaults.colors(
                    Color.White
                ),
                modifier = Modifier
                    .width(320.dp)
                    .height(50.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Correo") },
                colors = TextFieldDefaults.colors(
                    Color.White
                ),
                modifier = Modifier
                    .width(320.dp)
                    .height(50.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = pass.value,
                onValueChange = { pass.value = it },
                label = { Text("Contraseña") },
                colors = TextFieldDefaults.colors(
                    Color.White
                ),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .width(320.dp)
                    .height(50.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {  },
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4A6E4D)
                )
            ) {
                Text(text = "Registrarse")
            }
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}