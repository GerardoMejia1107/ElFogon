package com.nullPointerSociety.elfogon.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nullPointerSociety.elfogon.data.repository.impl.AuthState
import com.nullPointerSociety.elfogon.ui.screens.admin.dashboard.DashboardViewModel
import com.nullPointerSociety.elfogon.ui.screens.client.home.HomeViewModel
import com.nullPointerSociety.elfogon.ui.screens.common.auth.login.LoginScreen
import com.nullPointerSociety.elfogon.ui.screens.common.auth.login.LoginViewModel
import com.nullPointerSociety.elfogon.ui.screens.common.auth.register.RegisterScreen
import com.nullPointerSociety.elfogon.viewmodel.UserViewModel

@Composable
fun MainNavHost(
    navController: NavHostController,
) {
    val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory)
    //Solamente usado en el LaunchedEffect para redirigir al usuario si no está autenticado
    val authState = loginViewModel.authState.collectAsState()

    NavHost(navController = navController, startDestination = LogInScreenNav) {
        composable<LogInScreenNav> {
            LoginScreen(navController)
        }
        composable<SignUpScreenNav> {
            RegisterScreen(navController)
        }
    }
}
