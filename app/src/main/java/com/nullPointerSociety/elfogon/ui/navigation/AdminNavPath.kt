package com.nullPointerSociety.elfogon.ui.navigation

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nullPointerSociety.elfogon.data.repository.impl.AuthState
import com.nullPointerSociety.elfogon.ui.screens.admin.DashboardScreen
import com.nullPointerSociety.elfogon.ui.screens.common.auth.login.LoginViewModel
import com.nullPointerSociety.elfogon.ui.screens.client.home.HomeViewModel
import com.nullPointerSociety.elfogon.ui.screens.common.profile.ProfileScreen

@Composable
fun AdminNavPath(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    titleScreen: MutableState<String>,
) {
    val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory)
    val homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)

    val authState = loginViewModel.authState.collectAsState()
    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Unauthenticated -> navController.navigate(LogInScreenNav)
            else -> Unit
        }
    }

    NavHost(navController = navController, startDestination = DashboardScreenNav) {
        composable<DashboardScreenNav> {
            DashboardScreen(navController = navController, homeViewModel = homeViewModel)
        }

        composable<UsersScreenNav> {
        }

        composable<RegistrationsScreenNav> {
        }

        composable<ProfileScreenNav> {
            ProfileScreen(modifier)
        }


    }

}
