package com.nullPointerSociety.elfogon.ui.screens

import android.widget.Toast
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nullPointerSociety.elfogon.ui.navigation.HomeScreenNav
import com.nullPointerSociety.elfogon.viewmodel.AuthState
import com.nullPointerSociety.elfogon.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController, authViewModel: AuthViewModel) {
    val name = remember { mutableStateOf("") }
    val lastname = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val pass = remember { mutableStateOf("") }

    val auth = authViewModel.authState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(auth.value) {
        when (auth.value) {
            is AuthState.Authenticated -> navController.navigate(HomeScreenNav)
            is AuthState.Error -> Toast.makeText(
                context,
                (auth.value as AuthState.Error).message,
                Toast.LENGTH_LONG
            ).show()
            else -> Unit
        }
    }

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
                label = { Text("Nombres") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = lastname.value,
                onValueChange = { lastname.value = it },
                label = { Text("Apellidos") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Correo") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = pass.value,
                onValueChange = { pass.value = it },
                label = { Text("Contraseña") },

                visualTransformation = PasswordVisualTransformation(),

                )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    authViewModel.signUp(email = email.value, password = pass.value)

                },
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
