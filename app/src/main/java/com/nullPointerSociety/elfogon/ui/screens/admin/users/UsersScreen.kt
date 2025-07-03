package com.nullPointerSociety.elfogon.ui.screens.admin.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nullPointerSociety.elfogon.ui.components.admin.UsersList

@Composable
fun UsersScreen(
    modifier: Modifier = Modifier,
    scrollState: LazyListState = LazyListState(0, 0),
) {
    val usersAdminViewModel: UsersViewModel = viewModel(factory = UsersViewModel.Factory)
    val usersOnTheApp = usersAdminViewModel.allUsers.collectAsState().value

    LazyColumn(
        state = scrollState,
        modifier = modifier
            .fillMaxSize()
            .padding(top = 20.dp),
        contentPadding = PaddingValues(bottom = 125.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            UsersList(usersOnTheApp)
        }
    }

}