package com.nullPointerSociety.elfogon.ui.screens.admin.registrations

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nullPointerSociety.elfogon.ui.components.admin.CountCardHorizontal
import com.nullPointerSociety.elfogon.ui.components.admin.UsersList

@Composable
fun RegistrationsScreen(
    modifier: Modifier = Modifier,
    scrollState: LazyListState = LazyListState(0, 0),
) {
    val registrationsViewModel: RegistrationsViewModel =
        viewModel(factory = RegistrationsViewModel.Factory)
    val usersToday = registrationsViewModel.usersToday.collectAsState().value

    LazyColumn(
        state = scrollState,
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(bottom = 125.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            CountCardHorizontal(
                usersToday.size,
                "Registrations Today",
                "Total amount of users registered today"
            )
        }
        item {
            UsersList(users = usersToday.sortedByDescending { it.role.lowercase() == "admin" }
            )
        }
    }

}