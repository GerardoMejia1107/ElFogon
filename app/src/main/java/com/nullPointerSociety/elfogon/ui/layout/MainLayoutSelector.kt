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
import com.nullPointerSociety.elfogon.ui.layout.client.CustomScaffold
import com.nullPointerSociety.elfogon.ui.screens.auth.login.LoginScreen
import com.nullPointerSociety.elfogon.ui.screens.auth.login.LoginViewModel
import com.nullPointerSociety.elfogon.viewmodel.UserViewModel

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun MainLayoutSelector(
) {
    val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory)
    val authState by loginViewModel.authState.collectAsState()
    val userData by loginViewModel.userData.collectAsState()

    val navController = rememberNavController()


    when {
        authState is AuthState.Authenticated && userData?.role == "admin" -> {
            CustomAdminScaffold()
        }

        authState is AuthState.Authenticated && userData?.role != null -> {
            CustomScaffold()
        }

        authState is AuthState.Unauthenticated -> {
            LoginScreen(navController)
        }

        else -> {
            LoginScreen(navController)
        }
    }
}