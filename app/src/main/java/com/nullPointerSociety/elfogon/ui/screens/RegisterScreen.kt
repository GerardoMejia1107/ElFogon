package com.nullPointerSociety.elfogon.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nullPointerSociety.elfogon.R
import com.nullPointerSociety.elfogon.data.repository.firebase.auth.AuthState
import com.nullPointerSociety.elfogon.ui.navigation.HomeScreenNav
import com.nullPointerSociety.elfogon.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
    specifyRoute: MutableState<String>,
) {
    specifyRoute.value = "register"
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
            Row(verticalAlignment = Alignment.Bottom, modifier = Modifier.padding(bottom = 30.dp)) {
                Image(
                    painter = painterResource(R.drawable.elfogon_logo),
                    contentDescription = "Del Fogon Logo",
                    modifier = Modifier.size(90.dp)
                )
                Text(
                    text = "Del Fogón",
                    fontSize = 45.sp,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,

                    )
            }

            Spacer(modifier = Modifier.height(24.dp))

            TextField(
                colors = TextFieldDefaults.colors(unfocusedContainerColor = MaterialTheme.colorScheme.background),
                value = name.value,
                onValueChange = { name.value = it },
                label = { Text("Nombres") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                colors = TextFieldDefaults.colors(unfocusedContainerColor = MaterialTheme.colorScheme.background),
                value = lastname.value,
                onValueChange = { lastname.value = it },
                label = { Text("Apellidos") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                colors = TextFieldDefaults.colors(unfocusedContainerColor = MaterialTheme.colorScheme.background),
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Correo") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                colors = TextFieldDefaults.colors(unfocusedContainerColor = MaterialTheme.colorScheme.background),
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
