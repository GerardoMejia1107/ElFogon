package com.nullPointerSociety.elfogon.ui.screens.admin

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nullPointerSociety.elfogon.data.repository.impl.AuthState
import com.nullPointerSociety.elfogon.ui.navigation.LogInScreenNav
import com.nullPointerSociety.elfogon.ui.screens.home.HomeViewModel

@Composable
fun DashboardScreen(
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    navController: NavController, homeViewModel: HomeViewModel = viewModel()
) {
    val authState by homeViewModel.authState.collectAsState()

    LaunchedEffect(authState) {
        if (authState is AuthState.Unauthenticated) {
            navController.navigate(LogInScreenNav)
        }
    }

    Column {

    }
}