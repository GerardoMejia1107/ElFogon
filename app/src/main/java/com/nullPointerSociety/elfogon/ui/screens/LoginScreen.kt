package com.nullPointerSociety.elfogon.ui.screens

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nullPointerSociety.elfogon.R
import com.nullPointerSociety.elfogon.data.repository.firebase.auth.AuthState
import com.nullPointerSociety.elfogon.ui.navigation.HomeScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.SignUpScreenNav
import com.nullPointerSociety.elfogon.utils.GoogleSignUtils
import com.nullPointerSociety.elfogon.viewmodel.AuthViewModel


@Composable
fun LoginScreen(navController: NavController, authViewModel: AuthViewModel) {
    val email = remember { mutableStateOf("") }
    val pass = remember { mutableStateOf("") }
    var showPassword = remember { mutableStateOf(false) }

    val auth = authViewModel.authState.collectAsState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        GoogleSignUtils.doGoogleSignIn(
            context = context,
            scope = scope,
            launcher = null,
            onCredentialReady = {
                GoogleSignUtils.doGoogleSignIn(
                    context = context,
                    scope = scope,
                    launcher = null,
                    onCredentialReady = { credential ->
                        authViewModel.signInWithGoogleCredential(credential)
                    }
                )
            }

        )
    }


    LaunchedEffect(auth.value) {
        when (auth.value) {
            is AuthState.Authenticated -> {
                Toast.makeText(context, "Bienvenido", Toast.LENGTH_SHORT).show()
                navController.navigate(HomeScreenNav)
            }

            is AuthState.Error -> Toast.makeText(
                context,
                (auth.value as AuthState.Error).message,
                Toast.LENGTH_LONG
            ).show()

            else -> Unit
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFBF7)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "DEL FOGÓN",
            fontSize = 30.sp,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        TextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Correo") },

            )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = pass.value,
            onValueChange = { pass.value = it },
            label = { Text("Contraseña") },
            trailingIcon = {
                IconButton(onClick = { showPassword.value = !showPassword.value }) {
                    Icon(
                        imageVector = Icons.Filled.RemoveRedEye,
                        contentDescription = "Mostrar contraseña",
                        tint = if (showPassword.value) Color(0xFF4A6E4D) else Color.Gray
                    )
                }
            },
            visualTransformation = if (!showPassword.value) PasswordVisualTransformation() else VisualTransformation.None,
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                authViewModel.login(email.value, pass.value)
            },
            modifier = Modifier
                .width(200.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4A6E4D)
            )
        ) {
            Text(text = "Iniciar Sesión", color = Color.White, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4A6E4D)
            ),
            onClick = {
                GoogleSignUtils.doGoogleSignIn(
                    context = context,
                    scope = scope,
                    launcher = launcher,
                    onCredentialReady = { credential ->
                        authViewModel.signInWithGoogleCredential(credential)
                    }
                )
            },

            ) {
            Icon(
                painter = painterResource(id = R.drawable.google),
                contentDescription = "Google Icon",
                modifier = Modifier
                    .size(25.dp),
            )

        }



        Spacer(modifier = Modifier.height(24.dp))
        // 🔥 Mensaje en vez de botón
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(text = "¿No tienes una cuenta? ")

            Text(
                text = "Regístrate",
                color = Color(0xFF4A6E4D),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clickable {
                        navController.navigate(SignUpScreenNav)
                    }
            )
        }
    }
}

