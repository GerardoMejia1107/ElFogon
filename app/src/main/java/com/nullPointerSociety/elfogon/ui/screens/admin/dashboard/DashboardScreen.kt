package com.nullPointerSociety.elfogon.ui.screens.admin.dashboard

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nullPointerSociety.elfogon.data.repository.impl.AuthState
import com.nullPointerSociety.elfogon.ui.components.admin.CountCard
import com.nullPointerSociety.elfogon.ui.components.admin.UsersList
import com.nullPointerSociety.elfogon.ui.navigation.LogInScreenNav
import com.nullPointerSociety.elfogon.ui.screens.client.home.HomeViewModel

@Composable
fun DashboardScreen(
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    navController: NavController, homeViewModel: HomeViewModel
) {
    val authState = homeViewModel.authState.collectAsState()
    val dashboardViewModel: DashboardViewModel = viewModel(factory = DashboardViewModel.Factory)

    val users = dashboardViewModel.allUsers.collectAsState().value
    val usersToday = dashboardViewModel.usersToday.collectAsState().value


    LaunchedEffect(authState.value) {
        if (authState.value is AuthState.Unauthenticated) {
            navController.navigate(LogInScreenNav)
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
    ) {
        item {
            Text(
                text = "See the users on the app",
                modifier = Modifier.padding(start = 16.dp, top = 3.dp),
                style = MaterialTheme.typography.titleMedium
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                CountCard(
                    modifier = Modifier.weight(1f),
                    users.size,
                    title = "Total Users",
                    description = "All users on the App"
                )
                CountCard(
                    modifier = Modifier.weight(1f),
                    usersToday.size,
                    title = "New Users Today",
                    description = "New users registered today"
                )
            }
        }
        item {
            Text(
                "List of users",
                modifier = Modifier.padding(start = 16.dp, top = 10.dp),
                style = MaterialTheme.typography.titleMedium
            )
            UsersList(
                users = users.sortedByDescending { it.role.lowercase() == "admin" }
            )
        }
    }
}