package com.nullPointerSociety.elfogon.ui.screens.admin

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nullPointerSociety.elfogon.data.repository.impl.AuthState
import com.nullPointerSociety.elfogon.ui.components.admin.UsersList
import com.nullPointerSociety.elfogon.ui.navigation.LogInScreenNav
import com.nullPointerSociety.elfogon.ui.screens.home.HomeViewModel

@Composable
fun DashboardScreen(
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    navController: NavController, homeViewModel: HomeViewModel
) {
    val authState = homeViewModel.authState.collectAsState()
    val dashboardViewModel: DashboardViewModel = viewModel(factory = DashboardViewModel.Factory)

    val users = dashboardViewModel.allUsers.collectAsState().value


    LaunchedEffect(authState.value) {
        if (authState.value is AuthState.Unauthenticated) {
            navController.navigate(LogInScreenNav)
        }
    }

    Column {
        UsersList(users)
    }
}