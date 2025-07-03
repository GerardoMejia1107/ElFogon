package com.nullPointerSociety.elfogon.ui.layout

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.nullPointerSociety.elfogon.data.repository.impl.AuthState
import com.nullPointerSociety.elfogon.ui.layout.admin.CustomAdminScaffold
import com.nullPointerSociety.elfogon.ui.layout.client.CustomClientScaffold
import com.nullPointerSociety.elfogon.ui.navigation.MainNavHost
import com.nullPointerSociety.elfogon.ui.screens.common.auth.login.LoginScreen
import com.nullPointerSociety.elfogon.ui.screens.common.auth.login.LoginViewModel

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun MainLayoutSelector(
) {
    //Para poder obtener el estado de autenticación y los datos del usuario
    val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory)
    val authState by loginViewModel.authState.collectAsState()
    val userData by loginViewModel.userData.collectAsState()

    //Para poder navegar entre pantallas
    val navController = rememberNavController()


    when {
        //Evaluo si el usuario está autenticado y es un administrador
        authState is AuthState.Authenticated && userData?.role == "admin" -> {
            CustomAdminScaffold()
        }

        //Evaluo si el usuario está autenticado y no es un administrador
        authState is AuthState.Authenticated && userData?.role != null -> {
            CustomClientScaffold()
        }

        else -> {
            MainNavHost(navController)
        }
    }
}