package com.nullPointerSociety.elfogon.ui.screens.admin.registrations

import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nullPointerSociety.elfogon.ui.screens.admin.users.UsersViewModel

@Composable
fun RegistrationsScreen(
    modifier: Modifier = Modifier,
    scrollState: LazyListState = LazyListState(0, 0),
) {
    val registrationsViewModel: RegistrationsViewModel =
        viewModel(factory = RegistrationsViewModel.Factory)
    val usersToday = registrationsViewModel.usersToday.collectAsState().value

    Log.d("RegistrationsScreen", "Users Today: $usersToday")

}